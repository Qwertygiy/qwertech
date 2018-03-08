package com.kbi.qwertech.loaders.mod;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.QTConfigs;
import gregapi.code.ModData;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ModLoad_JourneyMap extends ModLoadBase {

	@Override
	public ModData getMod() {
		// TODO Auto-generated method stub
		return new ModData("journeymap", "JourneyMap");
	}
	
	static void copyFromZip(String zipFilePath, String zipEntryName, File destDir, boolean overWrite)
			throws Throwable
	{
		if (zipEntryName.startsWith("/"))
		{
			zipEntryName = zipEntryName.substring(1);
		}
		ZipFile zipFile = new ZipFile(zipFilePath);
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();

		try
		{
			while (entry != null)
			{
				if (entry.getName().startsWith(zipEntryName))
				{
					File toFile = new File(destDir, entry.getName().split(zipEntryName)[1]);
					if ((overWrite) || (!toFile.exists()))
					{
						if (!entry.isDirectory())
						{
							Files.createParentDirs(toFile);
							new ZipEntryByteSource(zipFile, entry).copyTo(Files.asByteSink(toFile));
						}
					}
				}
  
				zipIn.closeEntry();
				entry = zipIn.getNextEntry();
			}
		}
		finally
		{
			zipIn.close();
		}
	}



	static void copyFromDirectory(File fromDir, File toDir, boolean overWrite)
			throws IOException
	{
		toDir.mkdir();
		File[] files = fromDir.listFiles();
 
		if (files == null)
		{
			throw new IOException(fromDir + " nas no files");
		}

		for (File from : files)
		{
			File to = new File(toDir, from.getName());
			if (from.isDirectory())
			{
				copyFromDirectory(from, to, overWrite);
			}
			else if ((overWrite) || (!to.exists()))
			{
				Files.copy(from, to);
			}
		}
	}
	
	public static boolean isInJar()
	{
		URL location = QwerTech.class.getProtectionDomain().getCodeSource().getLocation();
		return "jar".equals(location.getProtocol());
	}
	
	public static void copyResources(File targetDirectory, String assetsPath, String setName, boolean overwrite)
	{
		System.out.println("Starting attempt to copy resources at " + assetsPath + " to " + targetDirectory);
		String fromPath;
		File toDir;
		try
		{	
			URL resourceDir = QwerTech.class.getResource(assetsPath);
			String toPath = String.format("%s/%s", assetsPath, setName);
			toDir = new File(targetDirectory, setName);
			if (isInJar())
			{
				fromPath = java.net.URLDecoder.decode(resourceDir.getPath(), "utf-8").split("file:")[1].split("!/")[0];
				//copyFromZip.invoke(null, fromPath, toPath, toDir, overwrite);
				System.out.println("Attempting to call " + fromPath + " : " + toPath + " : " + toDir + " : " + overwrite);
				copyFromZip(fromPath, toPath, toDir, overwrite);
			}
			else
			{
				File fromDir = new File(QwerTech.class.getResource(toPath).getFile());
				fromPath = fromDir.getPath();
				copyFromDirectory(fromDir, toDir, overwrite);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerMobGear() {
		//this adds proper entity icons for journeymap
		try {
			//Class IconHandler = Class.forName("net.techbrew.journeymap.io.IconSetFileHandler");
			//Method getEntityIconDir = IconHandler.getMethod("getEntityIconDir");
			File dir = new File(Minecraft.getMinecraft().mcDataDir, "journeymap/icon/entity");
			if (!dir.exists())
			{
				dir.mkdirs();
			}
			for (String setName : new String[]{"2D", "3D"})
			{
				copyResources(dir, "/assets/qwertech/icon/entity", setName, QTConfigs.overwriteJourneyMap);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerMobScrapes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tweakRecipes() {
		// TODO Auto-generated method stub

	}
	
	private static class ZipEntryByteSource
	extends ByteSource
	{
		final ZipFile file;
		final ZipEntry entry;

		ZipEntryByteSource(ZipFile file, ZipEntry entry)
		{
			this.file = file;
			this.entry = entry;
		}

		public InputStream openStream()
				throws IOException
		{
			return file.getInputStream(entry);
		}

		public String toString()
		{
			return String.format("ZipEntryByteSource( %s / %s )", file, entry);
		}
	}

}
