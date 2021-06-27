package com.pi.activity;

import androidx.annotation.NonNull; import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pi.R;
import com.pi.activity.DAO.PecasDAO;
import com.pi.adapter.PecaAdapter;
import com.pi.model.Peca;
import com.pi.model.Preco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PecaAdapter.OnItemClickListener {

    public static final String EXTRA_SHOW = "EXTRA_SHOW";
    public static final String EXTRA_PRECOS = "EXTRA_PRECOS";
    public static final String CONTAGEM_FINAL ="CONTAGEM_FINAL";
    private static final int ADD_PIECE_REQUEST = 1;
    private static final int EDIT_PIECE_REQUEST = 2;
    private static final int ADD_PRECO_REQUEST = 6;
    private RecyclerView recyclerView;
    private PecaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private PecasDAO pecasDAO;

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
        List<String> nomeList=new ArrayList<>();
        for(Peca p: myDataSet){
            nomeList.add(p.getNome());
        }
        adapter = new PecaAdapter(myDataSet, this,nomeList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_PIECE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_PIECE_REQUEST) {
            if (resultCode == RESULT_OK){
                Peca peca = (Peca) data.getSerializableExtra(AddActivity.EXTRA_NEW_CONTACT);
                //pecasDAO.AdicionaItem(myDataSet,peca);
                myDataSet.add(peca);
                Collections.sort(myDataSet,new ComparatorNomeCrescente());
                adapter.notifyDataSetChanged();
            }
        }else if(requestCode == EDIT_PIECE_REQUEST){
            if(resultCode==RESULT_OK){
                Peca peca = (Peca) data.getSerializableExtra(ShowActivity.EXTRA_EDIT_PIECE);
                Peca peca2= (Peca) data.getSerializableExtra(ShowActivity.EXTRA_EDIT_PIECE2);
                int contagem= (int) data.getSerializableExtra(ShowActivity.CONTAGEM);
                Log.d(peca.getNome(),peca2.getNome());
                int i=0;
                for(Peca peca_data:myDataSet){
                    Log.d(peca_data.getNome(),peca.getNome());
                    String peca11=peca_data.getNome();
                    String peca22=peca.getNome();
                    //peca_data.setNome(peca2.getNome());
                    if(i==contagem){
                        //pecasDAO.EditaItem(peca_data,peca2);
                        peca_data.setTudo(peca2.getNome(),peca2.getCodigo(),peca2.getCategoria(),100,peca2.isAtiva());
                    }
                    i++;

                }
                Collections.sort(myDataSet,new ComparatorNomeCrescente());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        MenuItem searchItem= menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
        /*getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        List<String> nomeList2=new ArrayList<>();*//*
        for(Peca p: myDataSet){
            nomeList2.add(p.getNome());
        }
        PecaAdapter contatoAdapter=new PecaAdapter(myDataSet, this,nomeList2);*/
        //adapter = new PecaAdapter(myDataSet, this,nomeList2);
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                //Log.d("Teste","Teste");
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);*/
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
           /* case R.id.option_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_status:
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
                return true;*/
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

    public void RemoveItem(final int position) {
        /*Logger logger=Logger.getLogger(PecasDAO.class.getName());
        System.out.println(logger.toString());*/
        Log.d("teste","teste");
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ShowActivity.class);
        ArrayList<Preco> precoos = myDataSet.get(position).getPrecos();
        //Preco pr = myDataSet.get(position).getPrecos().get(0);
        //intent.putExtra(EXTRA_PRECOS,myDataSet.get(position).getPrecos());
        ShowActivity.precoos=precoos;
        Peca pecaa=new Peca(myDataSet.get(position).getNome(),myDataSet.get(position).getCodigo(),myDataSet.get(position).getCategoria(),myDataSet.get(position).isAtiva());
        intent.putExtra(EXTRA_SHOW, pecaa);
        //Log.d(Integer.toString(position), "terminar");
        //intent.putExtra(CONTAGEM_FINAL,Integer.toString(position));
        intent.putExtra(CONTAGEM_FINAL,position);
        startActivityForResult(intent,EDIT_PIECE_REQUEST);
    }

    private void CreateDataSet() {
        Preco preco1=new Preco(50,5,"25/06/2021");
        Preco preco2=new Preco(10,5,"25/06/2021");
        Preco preco3=new Preco(60,5,"25/06/2021");
        Preco preco4=new Preco(10,8,"25/06/2021");
        Preco preco5=new Preco(30,5,"25/06/2021");
        Preco preco6=new Preco(500,5,"25/06/2021");
        Preco preco7=new Preco(200,5,"25/06/2021");
        Preco preco8=new Preco(300,5,"25/06/2021");
        ArrayList<Preco> precos1= new ArrayList<Preco>();
        precos1.add(preco8);
        precos1.add(preco2);
        precos1.add(preco3);
        ArrayList<Preco> precos2= new ArrayList<Preco>();
        precos2.add(preco1);
        precos2.add(preco8);
        ArrayList<Preco> precos3= new ArrayList<Preco>();
        precos3.add(preco7);
        ArrayList<Preco> precos4= new ArrayList<Preco>();
        precos4.add(preco5);
        precos4.add(preco4);
        precos4.add(preco3);
        ArrayList<Preco> precos5= new ArrayList<Preco>();
        precos5.add(preco6);
        precos5.add(preco2);
        precos5.add(preco1);
        ArrayList<Preco> precos6= new ArrayList<Preco>();
        precos6.add(preco8);
        precos6.add(preco2);
        precos6.add(preco2);

        myDataSet.add(new Peca("Roda1", "12213", "Rodas", true,precos1));
        myDataSet.add(new Peca("Calota2", "32142", "Rodas", true,precos2));
        myDataSet.add(new Peca("Roda3", "34214", "Rodas", true,precos3));
        myDataSet.add(new Peca("Roda4", "21414", "Rodas", true,precos4));
        myDataSet.add(new Peca("Pneu5", "25913", "Rodas", true,precos5));
        myDataSet.add(new Peca("Roda6", "87599", "Rodas", true,precos6));
        myDataSet.add(new Peca("Carro7", "34764", "Rodas", true,precos1));
        myDataSet.add(new Peca("Roda8", "23414", "Rodas", true,precos6));
        Collections.sort(myDataSet,new ComparatorNomeCrescente());
    }
}
