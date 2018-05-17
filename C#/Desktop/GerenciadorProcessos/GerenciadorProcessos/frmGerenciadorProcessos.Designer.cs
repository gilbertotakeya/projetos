namespace GerenciadorProcessos
{
    partial class frmGerenciadorProcessos
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dtgvProcessos = new System.Windows.Forms.DataGridView();
            this.ID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Processo = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Prioridade = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Status = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.lblID = new System.Windows.Forms.Label();
            this.txtID = new System.Windows.Forms.TextBox();
            this.txtProcesso = new System.Windows.Forms.TextBox();
            this.lblProcesso = new System.Windows.Forms.Label();
            this.btnIncluir = new System.Windows.Forms.Button();
            this.btnSalvar = new System.Windows.Forms.Button();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnExcluir = new System.Windows.Forms.Button();
            this.cmbPrioridade = new System.Windows.Forms.ComboBox();
            this.lblPrioridade = new System.Windows.Forms.Label();
            this.btnAlterar = new System.Windows.Forms.Button();
            this.btnEscolherPasta = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dtgvProcessos)).BeginInit();
            this.SuspendLayout();
            // 
            // dtgvProcessos
            // 
            this.dtgvProcessos.AllowUserToAddRows = false;
            this.dtgvProcessos.AllowUserToDeleteRows = false;
            this.dtgvProcessos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dtgvProcessos.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.ID,
            this.Processo,
            this.Prioridade,
            this.Status});
            this.dtgvProcessos.Location = new System.Drawing.Point(15, 6);
            this.dtgvProcessos.Name = "dtgvProcessos";
            this.dtgvProcessos.ReadOnly = true;
            this.dtgvProcessos.Size = new System.Drawing.Size(598, 449);
            this.dtgvProcessos.TabIndex = 0;
            this.dtgvProcessos.CellEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.dtgvProcessos_CellEnter);
            // 
            // ID
            // 
            this.ID.HeaderText = "ID";
            this.ID.Name = "ID";
            this.ID.ReadOnly = true;
            this.ID.Width = 60;
            // 
            // Processo
            // 
            this.Processo.HeaderText = "Processo";
            this.Processo.Name = "Processo";
            this.Processo.ReadOnly = true;
            this.Processo.Width = 270;
            // 
            // Prioridade
            // 
            this.Prioridade.HeaderText = "Prioridade";
            this.Prioridade.Name = "Prioridade";
            this.Prioridade.ReadOnly = true;
            // 
            // Status
            // 
            this.Status.HeaderText = "Status";
            this.Status.Name = "Status";
            this.Status.ReadOnly = true;
            // 
            // lblID
            // 
            this.lblID.AutoSize = true;
            this.lblID.Location = new System.Drawing.Point(12, 458);
            this.lblID.Name = "lblID";
            this.lblID.Size = new System.Drawing.Size(18, 13);
            this.lblID.TabIndex = 1;
            this.lblID.Text = "ID";
            // 
            // txtID
            // 
            this.txtID.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtID.Location = new System.Drawing.Point(15, 473);
            this.txtID.Name = "txtID";
            this.txtID.ReadOnly = true;
            this.txtID.Size = new System.Drawing.Size(100, 20);
            this.txtID.TabIndex = 2;
            // 
            // txtProcesso
            // 
            this.txtProcesso.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtProcesso.Location = new System.Drawing.Point(121, 473);
            this.txtProcesso.Name = "txtProcesso";
            this.txtProcesso.ReadOnly = true;
            this.txtProcesso.Size = new System.Drawing.Size(266, 20);
            this.txtProcesso.TabIndex = 4;
            // 
            // lblProcesso
            // 
            this.lblProcesso.AutoSize = true;
            this.lblProcesso.Location = new System.Drawing.Point(118, 458);
            this.lblProcesso.Name = "lblProcesso";
            this.lblProcesso.Size = new System.Drawing.Size(51, 13);
            this.lblProcesso.TabIndex = 3;
            this.lblProcesso.Text = "Processo";
            // 
            // btnIncluir
            // 
            this.btnIncluir.Location = new System.Drawing.Point(384, 499);
            this.btnIncluir.Name = "btnIncluir";
            this.btnIncluir.Size = new System.Drawing.Size(75, 23);
            this.btnIncluir.TabIndex = 5;
            this.btnIncluir.Text = "Incluir";
            this.btnIncluir.UseVisualStyleBackColor = true;
            this.btnIncluir.Click += new System.EventHandler(this.btnIncluir_Click);
            // 
            // btnSalvar
            // 
            this.btnSalvar.Location = new System.Drawing.Point(96, 499);
            this.btnSalvar.Name = "btnSalvar";
            this.btnSalvar.Size = new System.Drawing.Size(75, 23);
            this.btnSalvar.TabIndex = 6;
            this.btnSalvar.Text = "Salvar";
            this.btnSalvar.UseVisualStyleBackColor = true;
            this.btnSalvar.Visible = false;
            this.btnSalvar.Click += new System.EventHandler(this.btnSalvar_Click);
            // 
            // btnCancelar
            // 
            this.btnCancelar.Location = new System.Drawing.Point(177, 499);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(75, 23);
            this.btnCancelar.TabIndex = 7;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = true;
            this.btnCancelar.Visible = false;
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click);
            // 
            // btnExcluir
            // 
            this.btnExcluir.Location = new System.Drawing.Point(538, 499);
            this.btnExcluir.Name = "btnExcluir";
            this.btnExcluir.Size = new System.Drawing.Size(75, 23);
            this.btnExcluir.TabIndex = 8;
            this.btnExcluir.Text = "Excluir";
            this.btnExcluir.UseVisualStyleBackColor = true;
            this.btnExcluir.Click += new System.EventHandler(this.btnExcluir_Click);
            // 
            // cmbPrioridade
            // 
            this.cmbPrioridade.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbPrioridade.Enabled = false;
            this.cmbPrioridade.FormattingEnabled = true;
            this.cmbPrioridade.Items.AddRange(new object[] {
            "Normal",
            "Idle",
            "High",
            "RealTime",
            "Outros"});
            this.cmbPrioridade.Location = new System.Drawing.Point(433, 472);
            this.cmbPrioridade.Name = "cmbPrioridade";
            this.cmbPrioridade.Size = new System.Drawing.Size(180, 21);
            this.cmbPrioridade.TabIndex = 9;
            // 
            // lblPrioridade
            // 
            this.lblPrioridade.AutoSize = true;
            this.lblPrioridade.Location = new System.Drawing.Point(430, 458);
            this.lblPrioridade.Name = "lblPrioridade";
            this.lblPrioridade.Size = new System.Drawing.Size(54, 13);
            this.lblPrioridade.TabIndex = 10;
            this.lblPrioridade.Text = "Prioridade";
            // 
            // btnAlterar
            // 
            this.btnAlterar.Location = new System.Drawing.Point(461, 499);
            this.btnAlterar.Name = "btnAlterar";
            this.btnAlterar.Size = new System.Drawing.Size(76, 23);
            this.btnAlterar.TabIndex = 11;
            this.btnAlterar.Text = "Alterar";
            this.btnAlterar.UseVisualStyleBackColor = true;
            this.btnAlterar.Click += new System.EventHandler(this.btnAlterar_Click);
            // 
            // btnEscolherPasta
            // 
            this.btnEscolherPasta.Location = new System.Drawing.Point(391, 472);
            this.btnEscolherPasta.Name = "btnEscolherPasta";
            this.btnEscolherPasta.Size = new System.Drawing.Size(38, 22);
            this.btnEscolherPasta.TabIndex = 12;
            this.btnEscolherPasta.Text = "...";
            this.btnEscolherPasta.UseVisualStyleBackColor = true;
            this.btnEscolherPasta.Click += new System.EventHandler(this.btnEscolherPasta_Click);
            // 
            // frmGerenciadorProcessos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.ClientSize = new System.Drawing.Size(625, 525);
            this.Controls.Add(this.btnEscolherPasta);
            this.Controls.Add(this.btnAlterar);
            this.Controls.Add(this.lblPrioridade);
            this.Controls.Add(this.cmbPrioridade);
            this.Controls.Add(this.btnExcluir);
            this.Controls.Add(this.btnCancelar);
            this.Controls.Add(this.btnSalvar);
            this.Controls.Add(this.btnIncluir);
            this.Controls.Add(this.txtProcesso);
            this.Controls.Add(this.lblProcesso);
            this.Controls.Add(this.txtID);
            this.Controls.Add(this.lblID);
            this.Controls.Add(this.dtgvProcessos);
            this.Name = "frmGerenciadorProcessos";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Gerenciador de Processos";
            ((System.ComponentModel.ISupportInitialize)(this.dtgvProcessos)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dtgvProcessos;
        private System.Windows.Forms.Label lblID;
        private System.Windows.Forms.TextBox txtID;
        private System.Windows.Forms.TextBox txtProcesso;
        private System.Windows.Forms.Label lblProcesso;
        private System.Windows.Forms.Button btnIncluir;
        private System.Windows.Forms.Button btnSalvar;
        private System.Windows.Forms.Button btnCancelar;
        private System.Windows.Forms.Button btnExcluir;
        private System.Windows.Forms.ComboBox cmbPrioridade;
        private System.Windows.Forms.Label lblPrioridade;
        private System.Windows.Forms.Button btnAlterar;
        private System.Windows.Forms.DataGridViewTextBoxColumn ID;
        private System.Windows.Forms.DataGridViewTextBoxColumn Processo;
        private System.Windows.Forms.DataGridViewTextBoxColumn Prioridade;
        private System.Windows.Forms.DataGridViewTextBoxColumn Status;
        private System.Windows.Forms.Button btnEscolherPasta;
    }
}

