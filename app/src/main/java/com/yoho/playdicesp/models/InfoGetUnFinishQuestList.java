package com.yoho.playdicesp.models;

public class InfoGetUnFinishQuestList
{
  public static boolean bSuccess;
  public static Result[] oResult;
  public static String sMessage;

  public static class Result
  {
    public String Bonus;
    public String Experience;
    public boolean Is_Finish;
    public String Quest_ID;
    public String Quest_Name;
    public String Quest_Type;
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\DiceGirl\com.yoho.playdicesp\classes_dex2jar.jar
 * Qualified Name:     com.yoho.playdicesp.models.InfoGetUnFinishQuestList
 * JD-Core Version:    0.5.4
 */