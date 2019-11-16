package id.co.plnntb.kpkrecyclerview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class Database(ctx: Context):
    ManagedSQLiteOpenHelper(ctx, "kpk",null,1) {

    //buat companion object agar object database bisa di akses oleh
    //semua activity
    companion object{
        private var instance: Database? = null
        //syncronize object database untuk memastikan semua activity
        //menggunakan object database yang sama
        @Synchronized
        fun getInstance(ctx: Context): Database{
            if (instance == null){
                instance = Database(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable("pelanggan", true,
            "idpel" to TEXT + PRIMARY_KEY,
            "nama" to TEXT,
            "alamat" to TEXT,
            "tarif" to TEXT,
            "daya" to INTEGER
            )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}

//akses property dari Context
val Context.database: Database
    get() = Database.getInstance(applicationContext)