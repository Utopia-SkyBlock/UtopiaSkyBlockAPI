package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.gui.items;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.jspecify.annotations.Nullable;
import xyz.xenondevs.invui.Click;
import xyz.xenondevs.invui.InvUI;
import xyz.xenondevs.invui.item.AbstractItem;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.ItemWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
public class ClickableItem extends AbstractItem
{

    private final HandleClick clickHandler;
    private final AtomicBoolean clickOnce;
    private final boolean isSimpleMode;
    private final int updatePeriod;
    private volatile Function<? super Player, ? extends ItemProvider> itemProvider;
    private ItemStack simpleItemStack;
    private @Nullable BukkitTask updateTask;

    private ClickableItem(HandleClick clickHandler, AtomicBoolean clickOnce, boolean isSimpleMode, Function<? super Player, ? extends ItemProvider> itemProvider, ItemStack simpleItemStack, int updatePeriod)
    {
        this.clickHandler = clickHandler;
        this.clickOnce = clickOnce;
        this.isSimpleMode = isSimpleMode;
        this.itemProvider = itemProvider;
        this.simpleItemStack = simpleItemStack;
        this.updatePeriod = updatePeriod;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    @Override
    public ItemProvider getItemProvider(Player viewer)
    {
        if(isSimpleMode)
        {
            return new ItemWrapper(simpleItemStack);
        }
        return itemProvider.apply(viewer);
    }

    @Override
    public void handleClick(ClickType clickType, Player player, Click click)
    {
        if(clickHandler != null)
        {
            if(clickOnce == null || clickOnce.compareAndSet(false, true))
            {
                clickHandler.accept(clickType, player, click, this);
            }
        }
    }

    public void modifyItemStack(Consumer<ItemStack> itemModifier)
    {
        if(!isSimpleMode)
        {
            throw new IllegalStateException("Cannot modify ItemStack on non-simple ClickableItem!");
        }
        itemModifier.accept(simpleItemStack);
        notifyWindows();
    }

    public void setItemStack(ItemStack itemStack)
    {
        if(!isSimpleMode)
        {
            throw new IllegalStateException("Cannot set ItemStack on non-simple ClickableItem!");
        }
        simpleItemStack = itemStack.clone();
        notifyWindows();
    }

    public void resetClick()
    {
        if(clickOnce != null)
        {
            clickOnce.set(false);
        }
    }

    public static final class Builder
    {

        private HandleClick clickHandler;
        private AtomicBoolean clickOnce;
        private boolean isSimpleMode = false;
        private @Nullable Function<? super Player, ? extends ItemProvider> itemProviderFn;
        private ItemStack simpleItemStack;
        private @Nullable ItemProvider asyncPlaceholder;
        private @Nullable Supplier<? extends ItemProvider> asyncSupplier;
        private @Nullable CompletableFuture<? extends ItemProvider> asyncFuture;
        private Consumer<ClickableItem> modifier = item ->
        {};
        private boolean updateOnClick;
        private int updatePeriod = -1;

        private Builder(){}

        public Builder simple(ItemStack itemStack)
        {
            this.isSimpleMode = true;
            this.simpleItemStack = itemStack.clone();
            this.itemProviderFn = null;
            return this;
        }

        public Builder simple(ItemProvider itemProvider)
        {
            return setItemProvider(itemProvider);
        }

        public Builder setItemProvider(ItemProvider itemProvider)
        {
            this.isSimpleMode = false;
            this.itemProviderFn = viewer -> itemProvider;
            return this;
        }

        public Builder setItemProvider(Function<? super Player, ? extends ItemProvider> itemProvider)
        {
            this.isSimpleMode = false;
            this.itemProviderFn = itemProvider;
            return this;
        }

        public Builder setCyclingItemProvider(int period, List<? extends ItemProvider> itemProviders)
        {
            if(period <= 0)
            {
                throw new IllegalArgumentException("period must be greater than 0");
            }
            if(itemProviders.isEmpty())
            {
                throw new IllegalArgumentException("itemProviders must not be empty");
            }

            if(itemProviders.size() > 1)
            {
                updatePeriodically(period);
                this.isSimpleMode = false;
                this.itemProviderFn = viewer ->
                {
                    int i = (Bukkit.getCurrentTick() / period) % itemProviders.size();
                    return itemProviders.get(i);
                };
            }
            else
            {
                setItemProvider(itemProviders.get(0));
            }
            return this;
        }

        public Builder async(ItemProvider placeholder, Supplier<? extends ItemProvider> itemProviderSupplier)
        {
            this.asyncPlaceholder = placeholder;
            this.asyncSupplier = itemProviderSupplier;
            this.isSimpleMode = false;
            return this;
        }

        public Builder async(ItemProvider placeholder, CompletableFuture<? extends ItemProvider> itemProviderFuture)
        {
            this.asyncPlaceholder = placeholder;
            this.asyncFuture = itemProviderFuture;
            this.isSimpleMode = false;
            return this;
        }

        public Builder updatePeriodically(int period)
        {
            this.updatePeriod = period;
            return this;
        }

        public Builder updateOnClick()
        {
            this.updateOnClick = true;
            return this;
        }

        public Builder addClickHandler(HandleClick clickHandler)
        {
            this.clickHandler = clickHandler;
            return this;
        }

        public Builder addClickOnce()
        {
            this.clickOnce = new AtomicBoolean(false);
            return this;
        }

        public Builder addClickOnce(AtomicBoolean instance)
        {
            this.clickOnce = instance;
            return this;
        }

        public Builder addModifier(Consumer<? super ClickableItem> modifier)
        {
            this.modifier = this.modifier.andThen(modifier);
            return this;
        }

        public ClickableItem build()
        {
            if(updateOnClick)
            {
                HandleClick oldHandler = this.clickHandler;
                this.clickHandler = (clickType, player, click, item) ->
                {
                    if(oldHandler != null)
                    {
                        oldHandler.accept(clickType, player, click, item);
                    }
                    item.notifyWindows();
                };
            }

            ClickableItem customItem;
            if(asyncPlaceholder != null && !isSimpleMode)
            {
                customItem = new ClickableItem(clickHandler, clickOnce, false, viewer -> asyncPlaceholder, null, updatePeriod);

                if(asyncSupplier != null)
                {
                    Bukkit.getScheduler().runTaskAsynchronously(InvUI.getInstance().getPlugin(), () ->
                    {
                        var itemProvider = asyncSupplier.get();
                        customItem.itemProvider = viewer -> itemProvider;
                        customItem.notifyWindows();
                    });
                }
                else if(asyncFuture != null)
                {
                    asyncFuture.thenAccept(itemProvider ->
                    {
                        customItem.itemProvider = viewer -> itemProvider;
                        customItem.notifyWindows();
                    });
                }
            }
            else if(isSimpleMode)
            {
                if(asyncPlaceholder != null || asyncSupplier != null || asyncFuture != null)
                {
                    throw new IllegalStateException("Cannot use async with simple mode!");
                }
                customItem = new ClickableItem(clickHandler, clickOnce, true, null, simpleItemStack, updatePeriod);
            }
            else
            {
                customItem = new ClickableItem(clickHandler, clickOnce, false, itemProviderFn != null ? itemProviderFn : viewer -> ItemProvider.EMPTY, null, updatePeriod);
            }

            modifier.accept(customItem);

            return customItem;
        }

    }

}