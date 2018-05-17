namespace ControleDespesas
{
    partial class FrmPessoasEmpresas
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.MainMenu mainMenu1;

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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FrmPessoasEmpresas));
            this.mainMenu1 = new System.Windows.Forms.MainMenu();
            this.dtgPessoas = new System.Windows.Forms.DataGrid();
            this.lblNome = new System.Windows.Forms.Label();
            this.txtNome = new System.Windows.Forms.TextBox();
            this.txtEndereco = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.txtCidade = new System.Windows.Forms.TextBox();
            this.lblCidade = new System.Windows.Forms.Label();
            this.txtUF = new System.Windows.Forms.TextBox();
            this.lblUF = new System.Windows.Forms.Label();
            this.txtCPFCNPJ = new System.Windows.Forms.TextBox();
            this.lblCPFCNPJ = new System.Windows.Forms.Label();
            this.txtRGIE = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtCep = new System.Windows.Forms.TextBox();
            this.lblCEP = new System.Windows.Forms.Label();
            this.btCep = new System.Windows.Forms.Button();
            this.pctCancelar = new System.Windows.Forms.PictureBox();
            this.pctSalvar = new System.Windows.Forms.PictureBox();
            this.pctAdicionar = new System.Windows.Forms.PictureBox();
            this.txtReferencia = new System.Windows.Forms.TextBox();
            this.lblReferencia = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // dtgPessoas
            // 
            this.dtgPessoas.BackgroundColor = System.Drawing.Color.FromArgb(((int)(((byte)(128)))), ((int)(((byte)(128)))), ((int)(((byte)(128)))));
            this.dtgPessoas.Dock = System.Windows.Forms.DockStyle.Top;
            this.dtgPessoas.Location = new System.Drawing.Point(0, 0);
            this.dtgPessoas.Name = "dtgPessoas";
            this.dtgPessoas.Size = new System.Drawing.Size(240, 74);
            this.dtgPessoas.TabIndex = 0;
            // 
            // lblNome
            // 
            this.lblNome.Location = new System.Drawing.Point(1, 74);
            this.lblNome.Name = "lblNome";
            this.lblNome.Size = new System.Drawing.Size(234, 13);
            this.lblNome.Text = "Nome / Razao Social ou Nome Fantasia";
            // 
            // txtNome
            // 
            this.txtNome.Location = new System.Drawing.Point(3, 88);
            this.txtNome.Name = "txtNome";
            this.txtNome.Size = new System.Drawing.Size(234, 21);
            this.txtNome.TabIndex = 2;
            // 
            // txtEndereco
            // 
            this.txtEndereco.Location = new System.Drawing.Point(100, 160);
            this.txtEndereco.Name = "txtEndereco";
            this.txtEndereco.Size = new System.Drawing.Size(137, 21);
            this.txtEndereco.TabIndex = 4;
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(99, 146);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(62, 20);
            this.label1.Text = "Endereço";
            // 
            // txtCidade
            // 
            this.txtCidade.Location = new System.Drawing.Point(3, 199);
            this.txtCidade.Name = "txtCidade";
            this.txtCidade.Size = new System.Drawing.Size(199, 21);
            this.txtCidade.TabIndex = 7;
            // 
            // lblCidade
            // 
            this.lblCidade.Location = new System.Drawing.Point(1, 185);
            this.lblCidade.Name = "lblCidade";
            this.lblCidade.Size = new System.Drawing.Size(62, 14);
            this.lblCidade.Text = "Cidade";
            // 
            // txtUF
            // 
            this.txtUF.Location = new System.Drawing.Point(204, 199);
            this.txtUF.Name = "txtUF";
            this.txtUF.Size = new System.Drawing.Size(33, 21);
            this.txtUF.TabIndex = 10;
            // 
            // lblUF
            // 
            this.lblUF.Location = new System.Drawing.Point(204, 185);
            this.lblUF.Name = "lblUF";
            this.lblUF.Size = new System.Drawing.Size(31, 15);
            this.lblUF.Text = "UF";
            // 
            // txtCPFCNPJ
            // 
            this.txtCPFCNPJ.Location = new System.Drawing.Point(3, 125);
            this.txtCPFCNPJ.Name = "txtCPFCNPJ";
            this.txtCPFCNPJ.Size = new System.Drawing.Size(95, 21);
            this.txtCPFCNPJ.TabIndex = 13;
            // 
            // lblCPFCNPJ
            // 
            this.lblCPFCNPJ.Location = new System.Drawing.Point(1, 111);
            this.lblCPFCNPJ.Name = "lblCPFCNPJ";
            this.lblCPFCNPJ.Size = new System.Drawing.Size(70, 14);
            this.lblCPFCNPJ.Text = "CPF / CNPJ";
            // 
            // txtRGIE
            // 
            this.txtRGIE.Location = new System.Drawing.Point(100, 125);
            this.txtRGIE.Name = "txtRGIE";
            this.txtRGIE.Size = new System.Drawing.Size(137, 21);
            this.txtRGIE.TabIndex = 16;
            // 
            // label5
            // 
            this.label5.Location = new System.Drawing.Point(99, 111);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(67, 20);
            this.label5.Text = "RG / IE";
            // 
            // txtCep
            // 
            this.txtCep.Location = new System.Drawing.Point(23, 160);
            this.txtCep.Name = "txtCep";
            this.txtCep.Size = new System.Drawing.Size(75, 21);
            this.txtCep.TabIndex = 19;
            // 
            // lblCEP
            // 
            this.lblCEP.Location = new System.Drawing.Point(22, 147);
            this.lblCEP.Name = "lblCEP";
            this.lblCEP.Size = new System.Drawing.Size(62, 20);
            this.lblCEP.Text = "CEP";
            // 
            // btCep
            // 
            this.btCep.Location = new System.Drawing.Point(3, 160);
            this.btCep.Name = "btCep";
            this.btCep.Size = new System.Drawing.Size(19, 21);
            this.btCep.TabIndex = 21;
            this.btCep.Text = "...";
            // 
            // pctCancelar
            // 
            this.pctCancelar.Image = ((System.Drawing.Image)(resources.GetObject("pctCancelar.Image")));
            this.pctCancelar.Location = new System.Drawing.Point(204, 258);
            this.pctCancelar.Name = "pctCancelar";
            this.pctCancelar.Size = new System.Drawing.Size(34, 33);
            // 
            // pctSalvar
            // 
            this.pctSalvar.Image = ((System.Drawing.Image)(resources.GetObject("pctSalvar.Image")));
            this.pctSalvar.Location = new System.Drawing.Point(168, 258);
            this.pctSalvar.Name = "pctSalvar";
            this.pctSalvar.Size = new System.Drawing.Size(34, 33);
            // 
            // pctAdicionar
            // 
            this.pctAdicionar.Image = ((System.Drawing.Image)(resources.GetObject("pctAdicionar.Image")));
            this.pctAdicionar.Location = new System.Drawing.Point(132, 258);
            this.pctAdicionar.Name = "pctAdicionar";
            this.pctAdicionar.Size = new System.Drawing.Size(34, 33);
            // 
            // txtReferencia
            // 
            this.txtReferencia.Location = new System.Drawing.Point(3, 234);
            this.txtReferencia.Name = "txtReferencia";
            this.txtReferencia.Size = new System.Drawing.Size(234, 21);
            this.txtReferencia.TabIndex = 35;
            // 
            // lblReferencia
            // 
            this.lblReferencia.Location = new System.Drawing.Point(1, 220);
            this.lblReferencia.Name = "lblReferencia";
            this.lblReferencia.Size = new System.Drawing.Size(62, 13);
            this.lblReferencia.Text = "Referência";
            // 
            // FrmPessoas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 294);
            this.Controls.Add(this.txtReferencia);
            this.Controls.Add(this.lblReferencia);
            this.Controls.Add(this.pctAdicionar);
            this.Controls.Add(this.pctSalvar);
            this.Controls.Add(this.pctCancelar);
            this.Controls.Add(this.btCep);
            this.Controls.Add(this.txtCep);
            this.Controls.Add(this.lblCEP);
            this.Controls.Add(this.txtRGIE);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.txtCPFCNPJ);
            this.Controls.Add(this.lblCPFCNPJ);
            this.Controls.Add(this.txtUF);
            this.Controls.Add(this.lblUF);
            this.Controls.Add(this.txtCidade);
            this.Controls.Add(this.lblCidade);
            this.Controls.Add(this.txtEndereco);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtNome);
            this.Controls.Add(this.lblNome);
            this.Controls.Add(this.dtgPessoas);
            this.MinimizeBox = false;
            this.Name = "FrmPessoas";
            this.Text = "Pessoas / Empresas";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGrid dtgPessoas;
        private System.Windows.Forms.Label lblNome;
        private System.Windows.Forms.TextBox txtNome;
        private System.Windows.Forms.TextBox txtEndereco;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txtCidade;
        private System.Windows.Forms.Label lblCidade;
        private System.Windows.Forms.TextBox txtUF;
        private System.Windows.Forms.Label lblUF;
        private System.Windows.Forms.TextBox txtCPFCNPJ;
        private System.Windows.Forms.Label lblCPFCNPJ;
        private System.Windows.Forms.TextBox txtRGIE;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtCep;
        private System.Windows.Forms.Label lblCEP;
        private System.Windows.Forms.Button btCep;
        private System.Windows.Forms.PictureBox pctCancelar;
        private System.Windows.Forms.PictureBox pctSalvar;
        private System.Windows.Forms.PictureBox pctAdicionar;
        private System.Windows.Forms.TextBox txtReferencia;
        private System.Windows.Forms.Label lblReferencia;
    }
}