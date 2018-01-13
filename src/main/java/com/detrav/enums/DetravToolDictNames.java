package com.detrav.enums;

/**
 * Created by wital_000 on 19.03.2016.
 */
public enum  DetravToolDictNames {
    craftingToolProPick("craftingToolProPick"),
    craftingToolElectricProPick("craftingToolElectricProPick"),
    craftingToolPortableCharger("craftingToolPortableCharger"),
    craftingToolPortableAnvil("craftingToolPortableAnvil"),
    craftingToolSmartPlunger("craftingToolSmartPlunger"),
    craftingToolSmartTreeTap("craftingToolSmartTreeTap");

    private String mValue;

    DetravToolDictNames(String aValue)
    {
        mValue = aValue;
    }

    @Override
    public String toString() {
        return this.mValue;
    }
}
