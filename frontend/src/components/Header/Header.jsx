import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { FaBars, FaTimes } from 'react-icons/fa';
import { SiHiveBlockchain } from 'react-icons/si';
import styles from './Header.module.css';

export default function Header() {
  const [menuOpen, setMenuOpen] = useState(false);
  const location = useLocation();

  // futuramente essa info virá do backend
  const isLoggedIn = false;

  return (
    <header className={styles.header}>
      {/* Logo */}
      <Link to="/" className={styles.logo} onClick={() => setMenuOpen(false)}>
        <SiHiveBlockchain className={styles.logoIcon} />
        &lt; zeroScam &gt;
      </Link>

      {/* Botão hamburguer */}
      <button
        className={styles.hamburger}
        onClick={() => setMenuOpen(!menuOpen)}
        aria-label="Abrir menu"
      >
        {menuOpen ? <FaTimes /> : <FaBars />}
      </button>

      {/* Navegação */}
      <nav className={`${styles.nav} ${menuOpen ? styles.navOpen : ""}`}>
        <Link 
          to="/verificar" 
          className={`${styles.nav} ${location.pathname === '/verificar' ? styles.activeBtn : ''}`} 
          onClick={() => setMenuOpen(false)}
        >
          VERIFICAR LINK
        </Link>

        <Link 
          to="/denunciar" 
          className={location.pathname === '/denunciar' ? styles.active : ''} 
          onClick={() => setMenuOpen(false)}
        >
          DENUNCIAR GOLPE
        </Link>

        {/* Botão de Login ou Conta */}
          {isLoggedIn ? (
            <Link 
              to="/conta"
              className={`${styles.entrarBtn} ${location.pathname === '/conta' ? styles.activeBtn : ''}`}
              onClick={() => setMenuOpen(false)}
            >
              MINHA CONTA
            </Link>
          ) : (
            <Link 
              to="/login"
              className={`${styles.entrarBtn} ${location.pathname === '/login' ? styles.activeBtn : ''}`}
              onClick={() => setMenuOpen(false)}
            >
              ENTRAR
            </Link>
          )}
      </nav>
    </header>
  );
}
