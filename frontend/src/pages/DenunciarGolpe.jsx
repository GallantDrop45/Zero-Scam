import React, { useState } from 'react';
import { FaBars, FaTimes } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { SiHiveBlockchain } from "react-icons/si";
import styles from './DenunciarGolpe.module.css';

export default function DenunciarGolpe() {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
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
          <h1>Denunciar Golpe</h1>
        </div>
      </section>
  );
}
