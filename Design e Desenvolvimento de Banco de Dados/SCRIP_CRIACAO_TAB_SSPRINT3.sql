/*TABELA CIDADE*/
CREATE TABLE CIDADE(
    id NUMBER(10) NOT NULL,
    nome VARCHAR2(20) NOT NULL
);
/*Adicionando Constraints na tabela cidade*/
ALTER TABLE CIDADE ADD CONSTRAINT pk_cidade PRIMARY KEY(id);

/*TABELA ENDEREÇO*/
CREATE TABLE ENDERECO(
    id  NUMBER(10) NOT NULL,
    sigla VARCHAR2(2) NOT NULL,
    cidade NUMBER(10) NOT NULL
);
/*Criando constraints da tabela ENDEREÇO*/
ALTER TABLE ENDERECO ADD CONSTRAINT pk_endereco PRIMARY KEY(id);
ALTER TABLE ENDERECO ADD CONSTRAINT fk_endereco FOREIGN KEY(cidade)
REFERENCES CIDADE(id);

/*TABELA DEPARTAMENTO*/
CREATE TABLE DEPARTAMENTO(
    id NUMBER(10) NOT NULL,
    codigo NUMBER(10) NOT NULL,
    nome VARCHAR2(30) NOT NULL,
    qtd_funcionarios NUMBER (12,2)
);
/*Criando constraints da tabela FUNCIONARIO*/
ALTER TABLE DEPARTAMENTO ADD CONSTRAINT pk_departamento PRIMARY KEY(id);
ALTER TABLE DEPARTAMENTO ADD CONSTRAINT uk_departaemnto UNIQUE(codigo);

/*TABELA FUNCIONARIO*/
CREATE TABLE FUNCIONARIO(
    id  NUMBER(10) NOT NULL,
    codigo VARCHAR2(10) NOT NULL,
    nome VARCHAR2(30) NOT NULL,
    email VARCHAR2(30),
    telefone NUMBER(11),
    salario NUMBER(8,2) CONSTRAINT salario_menor_zero CHECK (salario < 0),
    dt_contratacao DATE DEFAULT SYSDATE,
    sexo VARCHAR2(20),
    endereco NUMBER(10) NOT NULL,
    departamento NUMBER(10) NOT NULL
);
/*Criando constraints da tabela FUNCIONARIO*/
ALTER TABLE FUNCIONARIO ADD CONSTRAINT pk_funcionario PRIMARY KEY(id);
ALTER TABLE FUNCIONARIO ADD CONSTRAINT uk_funcionario UNIQUE(codigo);

ALTER TABLE FUNCIONARIO ADD CONSTRAINT fk_funcionario_endereco
FOREIGN KEY (endereco) REFERENCES ENDERECO(id);

ALTER TABLE FUNCIONARIO ADD CONSTRAINT fk_funcionario_dep
FOREIGN KEY (departamento) REFERENCES DEPARTAMENTO(id);









