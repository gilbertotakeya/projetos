package representanetmobileadapter;

import java.util.ArrayList;

import com.br.representanetmobileclasses.*;
import com.br.representanetmobile.*;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class pedidoitensadapter extends BaseAdapter
{
	private Context ctx;
	private ArrayList<cPedidoItem> listaItensPedidos;
	
	public pedidoitensadapter(Context ct, ArrayList<cPedidoItem> listaItens)
	{
		this.ctx = ct;
		this.listaItensPedidos = listaItens;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.listaItensPedidos.size();
	}

	@Override
	public Object getItem(int posicao) {
		// TODO Auto-generated method stub		
		return listaItensPedidos.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		// TODO Auto-generated method stub
		return listaItensPedidos.get(posicao).id;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		cPedidoItem item = listaItensPedidos.get(posicao);
		View view = LayoutInflater.from(ctx).inflate(com.br.representanetmobile.R.layout.viewpedidoitens, null);
		TextView codigo = (TextView)view.findViewById(com.br.representanetmobile.R.id.viewpedidoitenscodigo);
		TextView descricao = (TextView)view.findViewById(com.br.representanetmobile.R.id.viewpedidoitensdescricao);
		TextView quantidade = (TextView)view.findViewById(com.br.representanetmobile.R.id.viewpedidoitensquantidade);
		TextView valor = (TextView)view.findViewById(com.br.representanetmobile.R.id.viewpedidoitensvalor);
		TextView valorTotal = (TextView)view.findViewById(com.br.representanetmobile.R.id.viewpedidoitensvalortotal);
		TextView complemento = (TextView)view.findViewById(com.br.representanetmobile.R.id.viewpedidoitenscomplemento);
		
		codigo.setText(item.cd_produto);
		descricao.setText(item.descricao);
		quantidade.setText(String.valueOf(item.qt_produto));
		valor.setText(String.valueOf(item.vr_unitario));
		valorTotal.setText(String.valueOf(item.vr_total));
		complemento.setText(String.valueOf(item.ds_complemento) + "");
		
		return view;
	}

}
