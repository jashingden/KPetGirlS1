package com.c9entertainment.pet.s1.crypto;

import android.util.Log;

import java.nio.ByteBuffer;

public class Crypto
{
  static
  {
    System.loadLibrary("keysets");
  }

  public static byte[] decrypt(ByteBuffer paramByteBuffer, int i)
  {
//    int i = paramByteBuffer.capacity();
    int j = i >> 2;
    int k = 0;
    ByteBuffer localByteBuffer = ByteBuffer.allocate(i);
    paramByteBuffer.position(0);
    int l = 0;
    if (l >= j);
    while (true)
    {
      if (i <= localByteBuffer.position()) {
        localByteBuffer.position(0);
        byte[] arrayOfByte = new byte[localByteBuffer.remaining()];
        localByteBuffer.get(arrayOfByte);
        return arrayOfByte;
      } else if (i < localByteBuffer.position()+4) {
        int c = i-localByteBuffer.position();
        for (int a=0; a<c; a++) {
          localByteBuffer.put(paramByteBuffer.get());
        }
      } else {
        int i1 = scramble(l & 0xF, k);
        int i2 = paramByteBuffer.getInt();
        localByteBuffer.putInt(i2 ^ i1);
        k = i2 ^ i1;
        ++l;
      }
//      localByteBuffer.put(paramByteBuffer.get());
    }
  }

  public static native int scramble(int paramInt1, int paramInt2);
}

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.crypto.Crypto
 * JD-Core Version:    0.5.4
 */