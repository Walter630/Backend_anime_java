CREATE TABLE IF NOT EXISTS users (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    favoritos TEXT
);

CREATE TABLE IF NOT EXISTS animes (
    id TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    genero TEXT NOT NULL,
    sinopse TEXT NOT NULL,
    data_lancamento TEXT NOT NULL,
    status TEXT NOT NULL,
    imagem TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS mangas (
    id TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    genero TEXT NOT NULL,
    sinopse TEXT NOT NULL,
    data_lancamento TEXT NOT NULL,
    status TEXT NOT NULL,
    imagem TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS videos (
    id TEXT PRIMARY KEY,
    anime_id TEXT NOT NULL,
    numero INTEGER NOT NULL,
    titulo TEXT,
    video_url TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS administradores (
    id TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    senha TEXT NOT NULL,
    role TEXT NOT NULL
);


