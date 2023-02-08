package com.example.elevai.contantes

class DataBaseConstantes private constructor() {

     object GUEST{

        const val ID = "GuestId"
        const val TABLE_NAME = "Guest"

         object COLUMNS{
             const val ID = "id"
             const val NAME = "name"
             const val PRESENCE = "presence"
         }
    }

}