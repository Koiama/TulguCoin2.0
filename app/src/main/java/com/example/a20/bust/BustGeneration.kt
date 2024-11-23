package com.example.a20.bust

import com.example.a20.R

object BustGeneration {


    private val imageIdList:List<Int> = listOf(
        R.drawable.nikita_bk,
        R.drawable.pasha_and_vodka,
        R.drawable.gde_papa,
        R.drawable.artem_kvadrober,
        R.drawable.redan
    )

    fun getBusts() : List<Bust>{
        return listOf(
            Bust(imageResId = imageIdList[0], name="Поздняков.Подписаться", count=1, lvl="1 lvl", 3500 ),
            Bust(imageResId = imageIdList[1], name="Приятного аппетита", count=1, lvl="1 lvl",333 ),
            Bust(imageResId = imageIdList[2], name="Пусть мама услышит", count=1, lvl="1 lvl", 123 ),
            Bust(imageResId = imageIdList[3], name="Хорошая погода", count=1, lvl="1 lvl",1 ),
            Bust(imageResId = imageIdList[4], name="Посетитель ламоды", count=1, lvl="1 lvl", 1488 ),
        )
    }
}