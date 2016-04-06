package com.c9entertainment.pet.s1.crypto;

import android.os.Build;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto
{
  private static final String HEX = "0123456789ABCDEF";
  private static final int JELLY_BEAN_4_2 = 17;

  private static void appendHex(StringBuffer paramStringBuffer, byte paramByte)
  {
    paramStringBuffer.append("0123456789ABCDEF".charAt(0xF & paramByte >> 4)).append("0123456789ABCDEF".charAt(paramByte & 0xF));
  }

  public static String decrypt(String paramString1, String paramString2)
    throws Exception
  {
    return new String(decrypt(getRawKey(paramString1.getBytes()), toByte(paramString2)));
  }

  private static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES");
    localCipher.init(2, localSecretKeySpec);
    return localCipher.doFinal(paramArrayOfByte2);
  }

  public static String encrypt(String paramString1, String paramString2)
    throws Exception
  {
    return toHex(encrypt(getRawKey(paramString1.getBytes()), paramString2.getBytes()));
  }

  private static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES");
    localCipher.init(1, localSecretKeySpec);
    return localCipher.doFinal(paramArrayOfByte2);
  }

  public static String fromHex(String paramString)
  {
    return new String(toByte(paramString));
  }

  private static byte[] getRawKey(byte[] paramArrayOfByte)
    throws Exception
  {
    KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
    if (Build.VERSION.SDK_INT >= 17);
    for (SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto"); ; localSecureRandom = SecureRandom.getInstance("SHA1PRNG"))
    {
      localSecureRandom.setSeed(paramArrayOfByte);
      localKeyGenerator.init(128, localSecureRandom);
      return localKeyGenerator.generateKey().getEncoded();
    }
  }

  public static byte[] toByte(String paramString)
  {
    int i = paramString.length() / 2;
    byte[] arrayOfByte = new byte[i];
    for (int j = 0; ; ++j)
    {
      if (j >= i)
        return arrayOfByte;
      arrayOfByte[j] = Integer.valueOf(paramString.substring(j * 2, 2 + j * 2), 16).byteValue();
    }
  }

  public static String toHex(String paramString)
  {
    return toHex(paramString.getBytes());
  }

  public static String toHex(byte[] paramArrayOfByte)
  {
    String str;
    if (paramArrayOfByte == null)
    {
      str = "";
      return str;
    }
    StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
    for (int i = 0; ; ++i)
    {
      if (i >= paramArrayOfByte.length) {
        str = localStringBuffer.toString();
        return str;
      }
      appendHex(localStringBuffer, paramArrayOfByte[i]);
    }
  }
}

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.crypto.SimpleCrypto
 * JD-Core Version:    0.5.4
 */