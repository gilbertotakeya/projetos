class CreateDonos < ActiveRecord::Migration[5.1]
  def change
    create_table :donos do |t|
      t.string :Nome
      t.string :Email
      t.string :Telefone
      t.string :Senha

      t.timestamps
    end
  end
end
