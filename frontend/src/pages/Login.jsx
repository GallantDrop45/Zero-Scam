import React, { useState } from "react";
import { SiHiveBlockchain } from "react-icons/si";
import Header from "../components/Header/Header";
import styles from "./Login.module.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const [errors, setErrors] = useState({
    email: "",
    senha: "",
    login: "",
  });

  const validateFields = () => {
    let valid = true;
    let newErrors = { email: "", senha: "", login: "" };

    // Validação do email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      newErrors.email = "Digite um email válido.";
      valid = false;
    }

    // Validação da senha
    if (senha.length < 8) {
      newErrors.senha = "A senha deve ter pelo menos 8 caracteres.";
      valid = false;
    }

    setErrors(newErrors);
    return valid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const isValid = validateFields();

    if (!isValid) return;

    // Integrar com o backend futuramente
    if (email === "teste@exemplo.com" && senha === "Senha123!") {
      console.log("Login bem-sucedido!");
      setErrors({ email: "", senha: "", login: "" });
    } else {
      setErrors({
        email: "",
        senha: "",
        login: "Email ou senha incorretos.",
      });
    }
  };

  return (
    <>
      <Header />
      <section className={styles.hero}>
        <div className={styles.card}>
          <SiHiveBlockchain className={styles.logoIcon} />
          <h2>Login.</h2>
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
            {errors.email && <span className={styles.error}>{errors.email}</span>}

            <label>SENHA</label>
            <input
              type="password"
              placeholder="Digite a sua senha"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
            />
            {errors.senha && <span className={styles.error}>{errors.senha}</span>}

            {errors.login && (
              <span className={`${styles.error} ${styles.loginError}`}>
                {errors.login}
              </span>
            )}

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
