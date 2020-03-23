package com.example.sqlitecase

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.add_dialog.view.add_et_name_Dialog
import kotlinx.android.synthetic.main.delete_dialog.*
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.delete_dialog.view.delete_et_name_Dialog
import kotlinx.android.synthetic.main.item_text.*
import kotlinx.android.synthetic.main.update_dialog.*
import kotlinx.android.synthetic.main.update_dialog.view.*
import kotlinx.android.synthetic.main.update_dialog.view.update_et_nameNEW_Dialog
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.update
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    val mutableMapOf = mutableMapOf<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        bt_update.setOnClickListener(View.OnClickListener {
            val lay = layoutInflater.inflate(R.layout.update_dialog, null)
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Information update")
                .setView(lay)
                .create()
                .show()
            lay.update_bt_name_Dialog.setOnClickListener(View.OnClickListener {
                var updatenameOld = lay.update_et_nameOLD_Dialog.text.toString()
                var updatenameNew = lay.update_et_nameNEW_Dialog.text.toString()
                if (updatenameOld.length > 0) {
                    val db = database.readableDatabase
                    val c =
                        db.rawQuery("select * from Info where name=?", arrayOf(updatenameOld))
                    if (c.moveToNext() == true) {
                        val c2 =
                            db.rawQuery("select * from Info where name=?", arrayOf(updatenameNew))
                        if (c2.moveToNext() == true) {
                            toast("Information already exists")
                        } else {

                            val numRowsDeleted =
                                db.update("Info", "name" to updatenameNew)
                                    .whereSimple("name=?", updatenameOld)
                                    .exec()
                            if (numRowsDeleted != null) {
                                toast("success update")
                                lay.update_et_nameNEW_Dialog.setText("")
                                lay.update_et_nameOLD_Dialog.setText("")
                                init()
                            } else {
                                toast("fail update")
                            }
                        }
                    } else {
                        toast("Information doesn't exist")
                    }
                } else {
                    toast("The input is empty")
                }
            })

        })

        bt_delete.setOnClickListener(View.OnClickListener {
            val lay = layoutInflater.inflate(R.layout.delete_dialog, null)
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Information delete")
                .setView(lay)
                .create()
                .show()
            lay.delete_bt_name_Dialog.setOnClickListener(View.OnClickListener {
                var deletename = lay.delete_et_name_Dialog.text.toString()
                if (deletename.length > 0) {
                    val db = database.writableDatabase
                    val c =
                        db.rawQuery("select * from Info where name=?", arrayOf(deletename))
                    if (c.moveToNext() == true) {
                        val numRowsDeleted =
                            db.delete("Info", "name={deletename}", "deletename" to deletename)
                        if (numRowsDeleted != null) {
                            toast("success delete")
                            lay.delete_et_name_Dialog.setText("")
                            init()
                        } else {
                            toast("fail delete")
                        }
                    } else {
                        toast("Information doesn't exist")
                    }
                } else {
                    toast("The input is empty")
                }

            })

        })


        bt_add.setOnClickListener(View.OnClickListener {
            val lay = layoutInflater.inflate(R.layout.add_dialog, null)
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Information add")
                .setView(lay)
                .create()
                .show()
            lay.add_bt_name_Dialog.setOnClickListener {
                var name = lay.add_et_name_Dialog.text.toString()
                val db = database.writableDatabase
                val values = ContentValues()
                values.put("name", name)
                if (name.length > 0) {
                    val cursor1 = db.rawQuery("select * from Info where name=?", arrayOf(name))
                    if (cursor1.moveToNext() == true) {
                        toast("Information already exists")
                    } else {
                        val cursor: Long = db.insert("Info", null, values)
                        if (cursor != null) {
                            toast("success add")
                            lay.add_et_name_Dialog.setText("")
                            init()
                        } else {
                            toast("fail add")
                        }
                    }
                } else {
                    toast("The input is empty")
                }

            }

        })
    }

    fun init() {
        val db1 = database.readableDatabase
        val cursor = db1.rawQuery("select * from Info ", null)
        mutableMapOf.clear()
        while (cursor.moveToNext() == true) {
            mutableMapOf.put(
                cursor.getInt(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("name"))
            )
            recyclerview.layoutManager = LinearLayoutManager(this)
            recyclerview.adapter = MyAdapter(this, mutableMapOf);
            recyclerview.setHasFixedSize(true)
        }
    }

    override fun onPause() {
        init()
        super.onPause()
    }

}



