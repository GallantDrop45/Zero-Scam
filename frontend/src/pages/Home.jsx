import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { FaMobileAlt, FaStar, FaShieldAlt, FaBars, FaTimes } from 'react-icons/fa';
import { SiHiveBlockchain } from "react-icons/si";
import styles from './Home.module.css';

export default function Home() {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <div className={styles.homeContainer}>
      {/* HERO */}
      <section className={styles.hero}>
        <header className={styles.header}>
        {/* Logo */}
        <Link to="/" className={styles.logo}>
          <SiHiveBlockchain className={styles.logoIcon} />
          &lt; zeroScam &gt;
        </Link>

          {/* Botão hamburguer no mobile */}
          <button 
            className={styles.hamburger} 
            onClick={() => setMenuOpen(!menuOpen)}
            aria-label="Abrir menu"
          >
            {menuOpen ? <FaTimes /> : <FaBars />}
          </button>

          {/* Nav */}
          <nav className={`${styles.nav} ${menuOpen ? styles.navOpen : ""}`}>
            <Link to="/" onClick={() => setMenuOpen(false)}>HOME</Link>
            <Link to="/denunciar" onClick={() => setMenuOpen(false)}>DENUNCIAR GOLPE</Link>
            <Link to="/verificar" className={styles.verificarBtn} onClick={() => setMenuOpen(false)}>
              VERIFICAR LINK
            </Link>
          </nav>
        </header>

        <div className={styles.heroContent}>
          <h1>
            Proteção simples e inteligente contra
            <br />
            links de golpe
          </h1>
          <Link to="/verificar" className={styles.cta}>
            VERIFICAR LINK AGORA
          </Link>
        </div>
      </section>

      {/* COMO FUNCIONA */}
      <section className={styles.comoFunciona}>
        <h2>COMO FUNCIONA</h2>
        <p>Em três passos simples, você verifica se um link é confiável</p>

        <div className={styles.steps}>
          <div className={styles.step}>
            <div className={styles.icon}><FaMobileAlt /></div>
            <h3>Cole o link <br />para verificar</h3>
            <p>Cole o link suspeito recebido por WhatsApp, SMS ou e-mail.</p>
          </div>

          <div className={styles.step}>
            <div className={styles.icon}><FaStar /></div>
            <h3>Receba o<br />Score</h3>
            <p>Veja instantaneamente o ranking de confiabilidade e alertas de segurança.</p>
          </div>

          <div className={styles.step}>
            <div className={styles.icon}><FaShieldAlt /></div>
            <h3>Proteção gratuita<br /> e acessível</h3>
            <p>Dicas práticas de segurança digital com base em análises rápidas e inteligentes</p>
          </div>
        </div>
      </section>

      {/* COMUNIDADE */}
      <section className={styles.comunidade}>
        <div className={styles.comunidadeContent}>
          <h2>PROTEJA A COMUNIDADE</h2>
          <p>Denuncie links perigosos e ajude a proteger outras pessoas.</p>
          <Link to="/denunciar" className={styles.cta}>DENUNCIE GOLPE</Link>

          <div className={styles.stats}>
            <div><strong>12.438</strong><p>Links verificados</p></div>
            <div><strong>3.207</strong><p>Golpes denunciados</p></div>
            <div><strong>98%</strong><p>Precisão em domínios</p></div>
          </div>
        </div>
      </section>

      {/* FOOTER */}
      <footer className={styles.footer}>
        <p>© 2025 ZeroScam — Protegendo pessoas contra golpes digitais.</p>
      </footer>
    </div>
  );
}
