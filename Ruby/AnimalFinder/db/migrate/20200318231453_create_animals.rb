class CreateAnimals < ActiveRecord::Migration[5.1]
  def change
    create_table :animals do |t|
      t.string :Nome
      t.string :Idade
      t.text :InformacoesExtras
      t.string :Cidade
      t.string :Estado
      t.string :Status
      t.integer :IdDono
      t.string :EncontradoPor
      t.string :TelefoneContato
      t.string :Localizacao

      t.timestamps
    end
  end
end
