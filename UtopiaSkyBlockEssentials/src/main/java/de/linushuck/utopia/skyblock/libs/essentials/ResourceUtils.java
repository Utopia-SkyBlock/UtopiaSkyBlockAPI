package de.linushuck.utopia.skyblock.libs.essentials;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ResourceUtils
{

    private static final Map<String, byte[]> fileContentsCache = new HashMap<>();

    /**
     * Loads and unzips the config ZIP data into memory
     *
     * @param zipData The ZIP file data as byte array
     */
    public static void loadConfigZipData(byte[] zipData)
    {
        fileContentsCache.clear(); // Clear cache when new data is set
        extractZipContents(zipData);
    }

    /**
     * Extracts all entries from the ZIP file directly into memory
     */
    private static void extractZipContents(byte[] zipData)
    {
        if(zipData == null)
        {
            return;
        }

        try(
                ByteArrayInputStream bais = new ByteArrayInputStream(zipData); ZipInputStream zis = new ZipInputStream(bais)
        )
        {

            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null)
            {
                if(!entry.isDirectory())
                {
                    String path = normalizeZipPath(entry.getName());
                    byte[] data = readAllBytes(zis);
                    fileContentsCache.put(path, data);
                }
                zis.closeEntry();
            }

            Logger.info(ResourceUtils.class, "Loaded " + fileContentsCache.size() + " files into memory");
        }
        catch(IOException e)
        {
            Logger.error(ResourceUtils.class, "Error extracting ZIP contents: " + e.getMessage());
        }
    }

    /**
     * Normalizes ZIP entry paths by removing the root "skyblock-configs/" prefix if present
     */
    private static String normalizeZipPath(String path)
    {
        if(path.startsWith("skyblock-configs/"))
        {
            return path.substring("skyblock-configs/".length());
        }
        return path;
    }

    /**
     * Gets a list of all file paths in a directory and its subdirectories
     *
     * @param folder Directory path (e.g., "items")
     * @return List of full file paths found in the specified folder and subfolders
     */
    public static List<String> getFolderContent(String folder)
    {
        // Normalize folder path
        if(!folder.endsWith("/"))
        {
            folder = folder + "/";
        }

        List<String> filePaths = new ArrayList<>();
        final String normalizedFolder = folder;

        // Filter cached entries for the specified folder
        fileContentsCache.keySet().stream().filter(path -> path.startsWith(normalizedFolder)).forEach(filePaths::add);

        return filePaths;
    }

    /**
     * Gets the content of a file
     *
     * @param filePath Path to the file (e.g., "config.json" or "items/sword.json")
     * @return The content of the file as a string, or null if not found
     */
    public static String getContentFromFile(String filePath)
    {
        byte[] data = fileContentsCache.get(filePath);
        if(data == null)
        {
            Logger.warn(ResourceUtils.class, "Could not find resource file: " + filePath);
            return null;
        }

        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * Gets an InputStream for a file
     *
     * @param filePath Path to the file
     * @return InputStream for the file, or null if not found
     */
    public static InputStream getResource(String filePath)
    {
        byte[] data = fileContentsCache.get(filePath);
        if(data == null)
        {
            Logger.warn(ResourceUtils.class, "Could not find resource file: " + filePath);
            return null;
        }

        return new ByteArrayInputStream(data);
    }

    /**
     * Gets raw byte data for a file
     *
     * @param filePath Path to the file
     * @return Byte array of file contents, or null if not found
     */
    public static byte[] getRawData(String filePath)
    {
        return fileContentsCache.get(filePath);
    }

    /**
     * Extracts just the filename from a full file path
     *
     * @param fullPath The full file path (e.g., "items/armor/helmet.json")
     * @return Just the filename (e.g., "helmet")
     */
    public static String getFileName(String fullPath)
    {
        return fullPath.substring(fullPath.lastIndexOf('/') + 1).replace(".json", "");
    }

    /**
     * Prints all resources in a folder
     *
     * @param folder The folder to list
     * @return String with all file paths, one per line
     */
    public static String printAllResourcesInFolder(String folder)
    {
        StringBuilder sb = new StringBuilder();
        List<String> filePaths = getFolderContent(folder);
        for(String filePath : filePaths)
        {
            sb.append(filePath).append("\n");
        }
        return sb.toString();
    }

    /**
     * Checks if a file exists
     *
     * @param filePath Path to the file
     * @return true if file exists, false otherwise
     */
    public static boolean fileExists(String filePath)
    {
        return fileContentsCache.containsKey(filePath);
    }

    /**
     * Gets all file paths
     *
     * @return Set of all file paths
     */
    public static Set<String> getAllFilePaths()
    {
        return new HashSet<>(fileContentsCache.keySet());
    }

    /**
     * Clears the cached data
     */
    public static void clearCache()
    {
        fileContentsCache.clear();
    }

    /**
     * Removes all files from cache that start with the given prefix
     * Useful for freeing memory after extracting large folders like world files
     *
     * @param prefix The prefix to match (e.g., "skyblock-world/")
     * @return Number of files removed
     */
    public static int removeByPrefix(String prefix)
    {
        if(!prefix.endsWith("/"))
        {
            prefix = prefix + "/";
        }

        final String finalPrefix = prefix;
        List<String> keysToRemove = fileContentsCache.keySet().stream()
                .filter(key -> key.startsWith(finalPrefix))
                .toList();

        keysToRemove.forEach(fileContentsCache::remove);

        int removed = keysToRemove.size();
        if(removed > 0)
        {
            Logger.info(ResourceUtils.class, "Removed " + removed + " files from cache (prefix: " + finalPrefix + ")");
        }

        return removed;
    }

    /**
     * Helper method to read all bytes from an InputStream
     */
    private static byte[] readAllBytes(InputStream is) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while((bytesRead = is.read(buffer)) != -1)
        {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    /**
     * Extracts a specific folder (like skyblock-world) to disk if needed
     * This is useful for the world folder which needs to be on disk
     *
     * @param folderInZip The folder path (e.g., "skyblock-world")
     * @param targetDir   The target directory on disk
     * @return true if successful, false otherwise
     */
    public static boolean extractFolderToDisk(String folderInZip, File targetDir) throws IOException
    {
        if(!folderInZip.endsWith("/"))
        {
            folderInZip = folderInZip + "/";
        }

        // Create target directory if it doesn't exist
        if(!targetDir.exists())
        {
            targetDir.mkdirs();
        }

        final String folder = folderInZip;
        boolean foundAny = false;

        // Extract all files in the specified folder
        for(Map.Entry<String, byte[]> entry : fileContentsCache.entrySet())
        {
            String path = entry.getKey();
            if(path.startsWith(folder))
            {
                foundAny = true;
                // Calculate relative path and create file
                String relativePath = path.substring(folder.length());
                File targetFile = new File(targetDir, relativePath);

                // Create parent directories if needed
                File parent = targetFile.getParentFile();
                if(!parent.exists())
                {
                    parent.mkdirs();
                }

                // Write file
                try(FileOutputStream fos = new FileOutputStream(targetFile))
                {
                    fos.write(entry.getValue());
                }
            }
        }

        if(!foundAny)
        {
            Logger.warn(ResourceUtils.class, "No files found in folder: " + folderInZip);
        }

        return foundAny;
    }
}