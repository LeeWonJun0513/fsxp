// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkerTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class MarkerTypeItem extends Item
{

    public MarkerTypeItem()
    {
        this("type");
    }

    public MarkerTypeItem(String name)
    {
        this.name = name;
        dataType = "Byte";
        offset = 0;
        length = 1;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        switch(tempData)
        {
        case 0: // '\0'
            decodedData = "INNER";
            break;

        case 1: // '\001'
            decodedData = "MIDDLE";
            break;

        case 2: // '\002'
            decodedData = "OUTER";
            break;

        case 3: // '\003'
            decodedData = "BACKCOURSE";
            break;
        }
    }
}
