package de.linushuck.utopia.skyblock.libs.api.skyblock.formatter;

import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor
public class TimeFormatter
{

    public String getTimeAgoString(long millis1)
    {
        return getTimeAgoString(System.currentTimeMillis(), millis1);
    }

    public String getTimeAgoString(long millis1, long millis2)
    {
        return getTimeAgoString(Duration.ofMillis(millis1 - millis2));
    }

    public String getTimeAgoString(Duration duration)
    {
        long seconds = duration.getSeconds();

        if(seconds == 0)
        {
            return "Just now";
        }

        if(seconds < 60)
        {
            return seconds + (seconds == 1 ? " second ago" : " seconds ago");
        }

        long minutes = seconds / 60;
        if(minutes < 60)
        {
            return minutes + (minutes == 1 ? " min ago" : " mins ago");
        }

        long hours = minutes / 60;
        if(hours < 24)
        {
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        }

        long days = hours / 24;
        return days + (days == 1 ? " day ago" : " days ago");
    }

    public String getFormattedTime(long durationInMillis)
    {
        return getFormattedTime(Duration.ofMillis(durationInMillis));
    }

    public String getFormattedTime(long millis1, long millis2)
    {
        return getFormattedTime(Duration.ofMillis(millis1 - millis2));
    }

    public String getFormattedTime(Duration duration)
    {
        if(duration.isZero())
        {
            return "00s";
        }

        long days = duration.toDays();
        duration = duration.minusDays(days);

        long hours = duration.toHours();
        duration = duration.minusHours(hours);

        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);

        long seconds = duration.getSeconds();

        StringBuilder formattedTime = new StringBuilder();
        if(days > 0)
        {
            formattedTime.append(String.format("%02dd ", days));
        }
        if(hours > 0)
        {
            formattedTime.append(String.format("%02dh ", hours));
        }
        if(minutes > 0)
        {
            formattedTime.append(String.format("%02dm ", minutes));
        }
        formattedTime.append(String.format("%2ds", seconds));

        if(duration.isNegative())
        {
            return formattedTime.toString().trim() + " ago";
        }

        return formattedTime.toString().trim();
    }
}
