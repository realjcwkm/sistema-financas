create database PlanejaGo;

use PlanejaGo;

create table tb_Usuario(
	id int not null auto_increment primary key,
    nome varchar(200) not null,
    email varchar(250) not null,
    data_nascimento date not null,
    senha varchar(8) not null,
    log_data_inclusao datetime not null default(current_date),
    constraint check_email Check(Email Like '%@%')  
);

create table tb_Tipo_Lancamento(
	id int not null auto_increment primary key,
    titulo varchar(30) not null,
    descricao varchar(100)
);

create table tb_Categoria(
	id int not null auto_increment primary key,
    titulo varchar(30) not null,
    descricao varchar(100),
    tipo int not null,
    constraint fk_tipo_categoria foreign key(tipo) references tb_Tipo_Lancamento(id)
);

create table tb_Centro_Custo(
	id int not null auto_increment primary key,
    titulo varchar(50) not null,
    descricao varchar(100)
);

create table tb_Frequencia(
	id int not null auto_increment primary key,
    titulo varchar(50) not null,
    descricao varchar(100)
);

create table tb_Lancamento(
	id int not null auto_increment primary key,
    descricao varchar(200),
    valor decimal(11,2) not null,
    status_pago bool default (False),
    categoria int not null, 
    data_criacao date default (current_date) not null,
    data_vencimento date default (current_date),
    frequencia int not null default(1),
    tipo int not null,
    log_data_inclusao datetime not null,
    log_data_alteracao datetime not null,
    log_versao_registro int not null default (1),
    usuario_id int not null,
    centro_custo int, 
    constraint fk_categoria foreign key(categoria) references tb_Categoria(id),
    constraint fk_frequencia foreign key(frequencia) references tb_Frequencia(id),
    constraint fk_tipo foreign key(tipo) references tb_Tipo_Lancamento(id),
    constraint fk_usuario_has_lancamento foreign key(usuario_id) references tb_Usuario(id),
    constraint fk_centro_custo_lancamento foreign key(centro_custo) references tb_Centro_Custo(id)
);

delimiter |
CREATE TRIGGER tg_atualiza_lancamento BEFORE UPDATE ON tb_Lancamento
FOR EACH ROW
BEGIN
    SET NEW.log_versao_registro = OLD.log_versao_registro + 1 ;
    SET NEW.log_data_alteracao = CURRENT_TIMESTAMP;
END;
|

-- Inserts básicos para o sistema funcionar

-- Tipo
insert into tb_Tipo_Lancamento (titulo) values
("Despesa"), 
("Receita");

-- Categoria
insert into tb_Categoria (titulo, tipo) values
("Moradia",1),
("Alimentação", 1),
("Saúde", 1), 
("Educação", 1),
("Transportes", 1),
("Lazer", 1),
("Salário", 2),
("Investimentos", 2), 
("Empréstimos", 2); 

-- Centro Custo
insert into tb_Centro_Custo (titulo) values
("Empresa de Água"),
("Empresa de Energia"),
("Operadora de Internet");

-- Frequencia
insert into tb_Frequencia(titulo) values
("Não se repete");
-- Demais serão inseridas em implementação futura
-- ("Diário"),
-- ("Mensal"),
-- ("Anual");
