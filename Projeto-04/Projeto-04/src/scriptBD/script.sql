-- Criar o banco de dados
CREATE DATABASE biblioteca;

-- Usar o banco de dados criado
USE biblioteca;

-- Criar a tabela de livros
CREATE TABLE livros (
    idlivro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    ano VARCHAR(4) NOT NULL
);

-- Criar a tabela de usuários
CREATE TABLE usuarios (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Criar a tabela de empréstimos
CREATE TABLE emprestimos (
    idemprestimo INT AUTO_INCREMENT PRIMARY KEY,
    idlivro INT NOT NULL,
    idusuario INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (idlivro) REFERENCES livros(idlivro),
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario)
);

-- Inserir alguns dados de exemplo na tabela de livros
INSERT INTO livros (titulo, autor, ano) VALUES 
('1984', 'George Orwell', '1949'),
('To Kill a Mockingbird', 'Harper Lee', '1960'),
('The Great Gatsby', 'F. Scott Fitzgerald', '1925');

-- Inserir alguns dados de exemplo na tabela de usuários
INSERT INTO usuarios (nome, email) VALUES 
('João da Silva', 'joao.silva@example.com'),
('Maria Oliveira', 'maria.oliveira@example.com'),
('Carlos Souza', 'carlos.souza@example.com');
