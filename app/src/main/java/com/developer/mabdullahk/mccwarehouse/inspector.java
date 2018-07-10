package com.developer.mabdullahk.mccwarehouse;

import java.util.ArrayList;

public class inspector
{
    ArrayList<String> attendance;
    String cnic;
    String email;
    String id;
    String name;
    String phone;
    ArrayList<String> warehouse;

    public inspector() {}

    public inspector(String paramString1, String paramString2, String paramString3, String paramString4, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2, String paramString5)
    {
        name = paramString1;
        phone = paramString2;
        cnic = paramString3;
        email = paramString4;
        warehouse = paramArrayList1;
        attendance = paramArrayList2;
        id = paramString5;
    }

    public ArrayList<String> getAttendance()
    {
        return attendance;
    }

    public String getCnic()
    {
        return cnic;
    }

    public String getEmail()
    {
        return email;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public ArrayList<String> getWarehouse()
    {
        return warehouse;
    }
}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.inspector

 * JD-Core Version:    0.7.0.1

 */