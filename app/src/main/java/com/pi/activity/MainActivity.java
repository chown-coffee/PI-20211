package com.pi.activity;

import androidx.annotation.NonNull; import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pi.R;
import com.pi.adapter.ContatoAdapter;
import com.pi.model.Peca;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContatoAdapter.OnItemClickListener {

    public static final String EXTRA_SHOW = "EXTRA_SHOW";
    private static final int ADD_CONTACT_REQUEST = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Peca> myDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateDataSet();

        recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContatoAdapter(myDataSet, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_CONTACT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK){
                Peca peca = (Peca) data.getSerializableExtra(AddActivity.EXTRA_NEW_CONTACT);
                myDataSet.add(peca);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
//            case R.id.option_order:
//                Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.option_favorites:
//                Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.option_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_status:
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case 222:
                //Toast.makeText(this, "Remove " + myDataSet.get(item.getGroupId()).getNome(), Toast.LENGTH_SHORT).show();
                RemoveItem(item.getGroupId());
                return true;
//            case 223:
                //Toast.makeText(this, "Share " + myDataSet.get(item.getGroupId()).getNome(), Toast.LENGTH_SHORT).show();
//                ShareItem(item.getGroupId());
//                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra(EXTRA_SHOW, myDataSet.get(position));
        startActivity(intent);
    }

    protected void RemoveItem(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Remove?");
        alertDialog.setMessage("Do you want to remove " + myDataSet.get(position).getNome() + "?");

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDataSet.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        alertDialog.setNegativeButton("Cancel", null);

        alertDialog.show();
    }

    private void CreateDataSet() {
        myDataSet.add(new Peca("Roda1", "12213", "Rodas", 180, true));
        myDataSet.add(new Peca("Roda2", "32142", "Rodas", 180, true));
        myDataSet.add(new Peca("Roda3", "34214", "Rodas", 210, true));
        myDataSet.add(new Peca("Roda4", "21414", "Rodas", 299, true));
        myDataSet.add(new Peca("Roda5", "25913", "Rodas", 99, true));
        myDataSet.add(new Peca("Roda6", "87599", "Rodas", 100, true));
        myDataSet.add(new Peca("Roda7", "34764", "Rodas", 150, true));
        myDataSet.add(new Peca("Roda8", "23414", "Rodas", 180, true));
    }
}
