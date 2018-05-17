namespace SimuladorGerenciadorMemoria
{
    partial class frmSimuladorProcessos
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.lblPrioridade = new System.Windows.Forms.Label();
            this.cmbPrioridade = new System.Windows.Forms.ComboBox();
            this.btnLocalizar = new System.Windows.Forms.Button();
            this.btnCriar = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.txtProcesso = new System.Windows.Forms.TextBox();
            this.dtgvProcessos = new System.Windows.Forms.DataGridView();
            this.ID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Descricao = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Prioridade = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Estado = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.EnderecoInicial = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.EnderecoFinal = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.btnAcordar = new System.Windows.Forms.Button();
            this.btnPausar = new System.Windows.Forms.Button();
            this.btnFinalizar = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dtgvProcessos)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.lblPrioridade);
            this.groupBox1.Controls.Add(this.cmbPrioridade);
            this.groupBox1.Controls.Add(this.btnLocalizar);
            this.groupBox1.Controls.Add(this.btnCriar);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.txtProcesso);
            this.groupBox1.Location = new System.Drawing.Point(3, 1);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(649, 87);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Criar processo";
            // 
            // lblPrioridade
            // 
            this.lblPrioridade.AutoSize = true;
            this.lblPrioridade.Location = new System.Drawing.Point(372, 16);
            this.lblPrioridade.Name = "lblPrioridade";
            this.lblPrioridade.Size = new System.Drawing.Size(54, 13);
            this.lblPrioridade.TabIndex = 1;
            this.lblPrioridade.Text = "Prioridade";
            // 
            // cmbPrioridade
            // 
            this.cmbPrioridade.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbPrioridade.FormattingEnabled = true;
            this.cmbPrioridade.Location = new System.Drawing.Point(375, 29);
            this.cmbPrioridade.Name = "cmbPrioridade";
            this.cmbPrioridade.Size = new System.Drawing.Size(268, 21);
            this.cmbPrioridade.TabIndex = 4;
            // 
            // btnLocalizar
            // 
            this.btnLocalizar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnLocalizar.Location = new System.Drawing.Point(343, 29);
            this.btnLocalizar.Name = "btnLocalizar";
            this.btnLocalizar.Size = new System.Drawing.Size(26, 21);
            this.btnLocalizar.TabIndex = 3;
            this.btnLocalizar.Text = "...";
            this.btnLocalizar.UseVisualStyleBackColor = true;
            this.btnLocalizar.Click += new System.EventHandler(this.btnLocalizar_Click);
            // 
            // btnCriar
            // 
            this.btnCriar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCriar.Location = new System.Drawing.Point(9, 59);
            this.btnCriar.Name = "btnCriar";
            this.btnCriar.Size = new System.Drawing.Size(634, 23);
            this.btnCriar.TabIndex = 1;
            this.btnCriar.Text = "&Criar";
            this.btnCriar.UseVisualStyleBackColor = true;
            this.btnCriar.Click += new System.EventHandler(this.btnCriar_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(51, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Processo";
            // 
            // txtProcesso
            // 
            this.txtProcesso.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtProcesso.Location = new System.Drawing.Point(9, 30);
            this.txtProcesso.Name = "txtProcesso";
            this.txtProcesso.Size = new System.Drawing.Size(328, 20);
            this.txtProcesso.TabIndex = 1;
            this.txtProcesso.Text = "C:\\Users\\Gilberto\\Desktop\\Trabalhos.txt";
            // 
            // dtgvProcessos
            // 
            this.dtgvProcessos.AllowUserToAddRows = false;
            this.dtgvProcessos.AllowUserToDeleteRows = false;
            this.dtgvProcessos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dtgvProcessos.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.ID,
            this.Descricao,
            this.Prioridade,
            this.Estado,
            this.EnderecoInicial,
            this.EnderecoFinal});
            this.dtgvProcessos.Location = new System.Drawing.Point(3, 93);
            this.dtgvProcessos.Name = "dtgvProcessos";
            this.dtgvProcessos.ReadOnly = true;
            this.dtgvProcessos.Size = new System.Drawing.Size(649, 264);
            this.dtgvProcessos.TabIndex = 1;
            this.dtgvProcessos.CellEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.dtgvProcessos_CellEnter);
            // 
            // ID
            // 
            this.ID.HeaderText = "IDProcesso";
            this.ID.Name = "ID";
            this.ID.ReadOnly = true;
            // 
            // Descricao
            // 
            this.Descricao.HeaderText = "Descricao";
            this.Descricao.Name = "Descricao";
            this.Descricao.ReadOnly = true;
            // 
            // Prioridade
            // 
            this.Prioridade.HeaderText = "Prioridade";
            this.Prioridade.Name = "Prioridade";
            this.Prioridade.ReadOnly = true;
            // 
            // Estado
            // 
            this.Estado.HeaderText = "Estado";
            this.Estado.Name = "Estado";
            this.Estado.ReadOnly = true;
            // 
            // EnderecoInicial
            // 
            this.EnderecoInicial.HeaderText = "Endereco Inicial";
            this.EnderecoInicial.Name = "EnderecoInicial";
            this.EnderecoInicial.ReadOnly = true;
            // 
            // EnderecoFinal
            // 
            this.EnderecoFinal.HeaderText = "Endereco Final";
            this.EnderecoFinal.Name = "EnderecoFinal";
            this.EnderecoFinal.ReadOnly = true;
            // 
            // btnAcordar
            // 
            this.btnAcordar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAcordar.Location = new System.Drawing.Point(3, 363);
            this.btnAcordar.Name = "btnAcordar";
            this.btnAcordar.Size = new System.Drawing.Size(75, 23);
            this.btnAcordar.TabIndex = 2;
            this.btnAcordar.Text = "&Acordar";
            this.btnAcordar.UseVisualStyleBackColor = true;
            this.btnAcordar.Click += new System.EventHandler(this.btnAcordar_Click);
            // 
            // btnPausar
            // 
            this.btnPausar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnPausar.Location = new System.Drawing.Point(84, 363);
            this.btnPausar.Name = "btnPausar";
            this.btnPausar.Size = new System.Drawing.Size(75, 23);
            this.btnPausar.TabIndex = 3;
            this.btnPausar.Text = "&Pausar";
            this.btnPausar.UseVisualStyleBackColor = true;
            this.btnPausar.Click += new System.EventHandler(this.btnPausar_Click);
            // 
            // btnFinalizar
            // 
            this.btnFinalizar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnFinalizar.Location = new System.Drawing.Point(165, 363);
            this.btnFinalizar.Name = "btnFinalizar";
            this.btnFinalizar.Size = new System.Drawing.Size(75, 23);
            this.btnFinalizar.TabIndex = 4;
            this.btnFinalizar.Text = "&Finalizar";
            this.btnFinalizar.UseVisualStyleBackColor = true;
            this.btnFinalizar.Click += new System.EventHandler(this.btnFinalizar_Click);
            // 
            // frmSimuladorProcessos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(659, 391);
            this.Controls.Add(this.btnFinalizar);
            this.Controls.Add(this.btnPausar);
            this.Controls.Add(this.btnAcordar);
            this.Controls.Add(this.dtgvProcessos);
            this.Controls.Add(this.groupBox1);
            this.Name = "frmSimuladorProcessos";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Simulador processos";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dtgvProcessos)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnLocalizar;
        private System.Windows.Forms.Button btnCriar;
        private System.Windows.Forms.Label lblPrioridade;
        private System.Windows.Forms.ComboBox cmbPrioridade;
        private System.Windows.Forms.DataGridView dtgvProcessos;
        private System.Windows.Forms.TextBox txtProcesso;
        private System.Windows.Forms.Button btnAcordar;
        private System.Windows.Forms.Button btnPausar;
        private System.Windows.Forms.Button btnFinalizar;
        private System.Windows.Forms.DataGridViewTextBoxColumn ID;
        private System.Windows.Forms.DataGridViewTextBoxColumn Descricao;
        private System.Windows.Forms.DataGridViewTextBoxColumn Prioridade;
        private System.Windows.Forms.DataGridViewTextBoxColumn Estado;
        private System.Windows.Forms.DataGridViewTextBoxColumn EnderecoInicial;
        private System.Windows.Forms.DataGridViewTextBoxColumn EnderecoFinal;
    }
}

