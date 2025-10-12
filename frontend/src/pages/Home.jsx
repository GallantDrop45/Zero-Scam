import React from 'react';
import { Link } from 'react-router-dom';
import { FaMobileAlt, FaStar, FaShieldAlt } from 'react-icons/fa';
import Header from '../components/Header/Header';
import styles from './Home.module.css';

export default function Home() {
    return (
    <div className={styles.homeContainer}>
      {/* HERO */}
      <section className={styles.hero}>
        {/* Header global */}
        <Header />

        <div className={styles.heroContent}>
          <h1>
            Proteção simples e inteligente contra<br />
            links de golpe
          </h1>
          <p className={styles.subtitulo}>
            Verifique qualquer link suspeito em segundos e navegue com mais segurança
          </p>

          <div className={styles.heroButtons}>
            <Link to="/denunciar" className={styles.btnSecondary}>
              DENUNCIAR UM GOLPE
            </Link>
            <Link to="/verificar" className={styles.cta}>
              VERIFICAR LINK AGORA
            </Link>
          </div>
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
            <p>Dicas práticas de segurança digital com base em análises rápidas e inteligentes.</p>
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
