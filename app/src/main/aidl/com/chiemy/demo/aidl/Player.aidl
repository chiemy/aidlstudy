// Player.aidl
package com.chiemy.demo.aidl;

import com.chiemy.demo.aidlstudy.Music;

interface Player {
    /**
     * 支持的数据类型
     * 基本数据类型，short类型除外
     * String
     * CharSequence
     * List，需要
     * 官方说支持所有基本数据类型，但实际是不支持short类型的，因为具体实现使用Parcel进行写入的，Parcel不能写入short
     */
//    void basicTypes(char aChar, int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);


    /**
     * Parcelable类型
     */
    void play(in Music music);

    /**
     * Parcelable类型
     */
    void current(out Music music);

    void pause();

    void next();

    void prev();
}
