package com.yoho.playdicesp.models;

public class InfoGetRoleFiles
{
  public boolean bSuccess;
  public Result[] oResult;
  public String sMessage;

  public static class Result
  {
    public String BWH;
    public String Category;
    public String Height;
    public images Images;
    public String Model_ID;
    public String Model_Name;
    public movies Movies;
    public setting Setting;
    public sounds Sounds;
    public String Weight;
    public String Year;

    public static class images
    {
      public String Image_Dialog;
      public String Image_Lock;
      public String Image_Unlock;
    }

    public static class movies
    {
      public String Video1_Open_Flag;
      public String Video2_Open_Flag;
      public String Video3_Open_Flag;
      public String Video4_Open_Flag;
      public String Video5_Open_Flag;
      public String Video6_Open_Flag;
      public String Video7_Open_Flag;
      public String Video8_Open_Flag;
      public String Video_File1;
      public String Video_File2;
      public String Video_File3;
      public String Video_File4;
      public String Video_File5;
      public String Video_File6;
      public String Video_File7;
      public String Video_File8;
      public String Video_PreviewImage1;
      public String Video_PreviewImage2;
      public String Video_PreviewImage3;
      public String Video_PreviewImage4;
      public String Video_PreviewImage5;
      public String Video_PreviewImage6;
      public String Video_PreviewImage7;
      public String Video_PreviewImage8;
    }

    public static class setting
    {
      public String Open_Bonus;
      public String Open_Bonus2;
      public String Open_Level;
      public String Video_Open1;
      public String Video_Open2;
      public String Video_Open3;
      public String Video_Open4;
      public String Video_Open5;
      public String Video_Open6;
      public String Video_Open7;
      public String Video_Open8;
    }

    public static class sounds
    {
      public String Sound_1;
      public String Sound_2;
      public String Sound_3;
      public String Sound_4;
      public String Sound_5;
      public String Sound_6;
      public String Sound_7;
      public String Sound_8;
    }
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\DiceGirl\com.yoho.playdicesp\classes_dex2jar.jar
 * Qualified Name:     com.yoho.playdicesp.models.InfoGetRoleFiles
 * JD-Core Version:    0.5.4
 */