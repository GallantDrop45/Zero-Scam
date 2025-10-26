import React, { useState } from "react";
import { SiHiveBlockchain } from 'react-icons/si';
import Header from "../components/Header/Header";
import styles from "./Login.module.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log({ email, senha });
  };

  return (
    <>
      <Header />
      <section className={styles.hero}>

        <div className={styles.card}>
          <SiHiveBlockchain className={styles.logoIcon} />
          <h2>login.</h2>
          <p className={styles.subtitle}>
            Acesse sua conta para continuar explorando nossa comunidade.
          </p>
          <hr className={styles.divider} />

          <form onSubmit={handleSubmit} className={styles.form}>
            <label>EMAIL</label>
            <input
              type="email"
              placeholder="Digite o seu email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <label>SENHA</label>
            <input
              type="password"
              placeholder="Digite a sua senha"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
            />

            <button type="submit" className={styles.btn}>
              ENTRAR
            </button>
          </form>

          <div className={styles.footerLinks}>
            <a href="/recuperar-senha" className={styles.forgotPassword}>
                Esqueceu a senha?
            </a>
            <p className={styles.signupText}>
                Ainda não tem uma conta?{" "}
                <a href="/cadastro" className={styles.signupLink}>
                Faça o seu cadastro
                </a>
            </p>
          </div>
        </div>
      </section>
    </>
  );
}
