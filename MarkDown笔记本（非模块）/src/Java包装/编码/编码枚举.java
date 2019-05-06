/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java包装.编码;

/**
 *
 * @author yanxi
 */
public enum 编码枚举 { 
    /*
        public static String GB2312 = "GB2312"; 
		public static String UTF8 = "UTF-8"; 
		public static String GBK = "GBK"; 
		public static String USASCII = "US-ASCII"; 
		public static String ISO88591 = "ISO-8859-1"; 
		public static String UTF16BE = "UTF-16BE"; 
		public static String UTF16LE = "UTF-16LE"; 
		public static String UTF16 = "UTF-16";
    */
    GB2312(         "GB2312",       "GB2312",       "GB-2312"),
    GBK(            "GBK",          "GBK",          "GBK"),
    GB18030(        "GB18030",      "GB18030",      "GB18030"),
    HZ(             "ASCII",        "HZ-GB-2312",   "HZ"),
    BIG5(           "BIG5",         "BIG5",         "Big5"),
    CNS11643(       "EUC-TW",       "EUC-TW",       "CNS11643"),
    UTF8(           "UTF-8",        "UTF-8",        "UTF-8"),
    UTF8T(          "UTF-8",        "UTF-8",        "UTF-8 (Trad)"),
    UTF8S(          "UTF-8",        "UTF-8",        "UTF-8 (Simp)"),
    UNICODE(        "Unicode",      "UTF-16",       "Unicode"),
    UNICODET(       "Unicode",      "UTF-16",       "Unicode (Trad)"),
    UNICODES(       "Unicode",      "UTF-16",       "Unicode (Simp)"),
    ISO2022CN(      "ISO2022CN",    "ISO2022CN",    "ISO2022 CN"),
    ISO2022CN_CNS(  "ISO2022CN_CNS","ISO2022CN_CNS","ISO2022CN-CNS"),
    ISO2022CN_GB(   "ISO2022CN_GB", "ISO2022CN_GB", "ISO2022CN-GB"),
    EUC_KR(         "EUC_KR",       "EUC_KR",       "EUC-KR"),
    CP949(          "MS949",        "x-windows-949","CP949"),
    ISO2022KR(      "ISO2022KR",    "ISO-2022-KR",  "ISO 2022 KR"),
    JOHAB(          "Johab",        "x-Johab",      "Johab"),
    SJIS(           "SJIS",         "Shift_JIS",    "Shift-JIS"),
    EUC_JP(         "EUC_JP",       "EUC-JP",       "EUC-JP"),
    ISO2022JP(      "ISO2022JP",    "ISO-2022-JP",  "ISO 2022 JP"),
    ASCII(          "ASCII",        "ASCII",        "ASCII"),
    OTHER(          "ISO8859_1",    "ISO8859_1",    "OTHER");
    
    public String java名称;
    public String html名称;
    public String 友好名称;
    编码枚举(String javaname,String htmlname,String nicename){
        this.java名称=javaname;
        this.html名称=htmlname;
        this.友好名称=nicename; 
    }
    
}
