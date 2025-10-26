import React, { useState } from "react";
import { SiHiveBlockchain } from "react-icons/si";
import Header from "../components/Header/Header";
import styles from "./Cadastro.module.css";

export default function Cadastro() {
  const [formData, setFormData] = useState({
    nome: "",
    sobrenome: "",
    email: "",
    senha: "",
    confirmarSenha: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
  };

  return (
    <>
      <Header />
      <section className={styles.hero}>
        <div className={styles.card}>
          <SiHiveBlockchain className={styles.logoIcon} />
          <h2>Cadastro.</h2>
          <p className={styles.subtitle}>
            Crie sua conta para acessar nossa comunidade.
          </p>
          <hr className={styles.divider} />

          <form onSubmit={handleSubmit} className={styles.form}>
            <div className={styles.row}>
              <div className={styles.inputGroup}>
                <label>Nome:</label>
                <input
                  type="text"
                  name="nome"
                  placeholder="Digite seu nome"
                  value={formData.nome}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className={styles.inputGroup}>
                <label>Sobrenome:</label>
                <input
                  type="text"
                  name="sobrenome"
                  placeholder="Digite seu sobrenome"
                  value={formData.sobrenome}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <label>Email:</label>
            <input
              type="email"
              name="email"
              placeholder="Digite seu email"
              value={formData.email}
              onChange={handleChange}
              required
            />

            <label>Senha:</label>
            <input
              type="password"
              name="senha"
              placeholder="Digite sua senha"
              value={formData.senha}
              onChange={handleChange}
              required
            />

            <label>Confirmar Senha:</label>
            <input
              type="password"
              name="confirmarSenha"
              placeholder="Confirme sua senha"
              value={formData.confirmarSenha}
              onChange={handleChange}
              required
            />

            <button type="submit" className={styles.btn}>
              Cadastrar
            </button>
          </form>

          <p className={styles.loginText}>
            Já tem uma conta?
            <a href="/login" className={styles.loginLink}>
              Faça o login
            </a>
          </p>
        </div>
      </section>
    </>
  );
}
