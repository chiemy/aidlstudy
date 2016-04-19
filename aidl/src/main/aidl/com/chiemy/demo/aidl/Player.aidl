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
     * Parcelable类型，in标识说明是输入参数，在方法内部对其属性进行修改，并不会影响原来对象的属性
     */
    void play(in Music music);

    /**
     * Parcelable类型，out说明为输出参数，在方法内部对其属性进行修改，那么原对象的属性也会跟着修改
     */
    void current(out Music music);

    void pause();

    void next();

    void prev();
}
