use bd_assistenciatecnica;
create table clientes(
id_cliente int primary key auto_increment not null,
nome varchar(200),
telefone varchar(20), 
email varchar(100),
endereco varchar(200),
id_os int,
id_equipamento int
);
alter table clientes 
	add constraint fk_idos foreign key (id_os)
	references ordemdeservico;
alter table clientes 
	add constraint fk_idequipamento foreign key(id_equipamento)
    references equipamento;
create table ordemdeservico(
id_os int primary key auto_increment not null,
servico varchar(300),
tecnico varchar(50),
valor double,
situacao varchar(50),
id_cliente int,
id_equipamento int,
constraint fk_idcli foreign key (id_cliente) references clientes (id_cliente)
);
alter table ordemdeservico 
	add constraint fk_idequipamento foreign key(id_equipamento) 
    references equipamento;
	

create table equipamento (
id_equipamento int primary key auto_increment not null,
marca varchar(50),
modelo varchar(50),
numero_serie int,
sistema_op varchar(40),
defeito varchar(300),
observacao varchar(300),
id_cliente int,
id_os int,
constraint fk_idcli foreign key(id_cliente) references clientes (id_cliente),
constraint fk_idos  foreign key(id_os) references ordemdeservico(id_os)
);


describe clientes;
describe ordemdeservico;
describe equipamento;


select * from clientes;
select * from ordemdeservico;
select * from equipamento;


select ordemdeservico.tecnico, ordemdeservico.servico,ordemdeservico.situacao,ordemdeservico.valor, equipamento.defeito,equipamento.marca,equipamento.tipo
from ordemdeservico 
inner join equipamento on ordemdeservico.id_os = equipamento.id_os;

ALTER TABLE equipamento add column tipo varchar(50);


select 
ordemdeservico.tecnico, 
ordemdeservico.servico,
ordemdeservico.situacao,
ordemdeservico.valor, 
equipamento.defeito,
equipamento.marca,
equipamento.tipo
from ordemdeservico 
inner join equipamento on ordemdeservico.id_os = equipamento.id_os;