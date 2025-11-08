import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { FaBars, FaTimes } from 'react-icons/fa';
import { SiHiveBlockchain } from 'react-icons/si';
import styles from './Header.module.css';

export default function Header() {
  const [menuOpen, setMenuOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const location = useLocation();

  
  useEffect(() => {
    const checkLogin = () => {
      const usuario = localStorage.getItem("usuario");
      setIsLoggedIn(!!usuario);
    };

    checkLogin(); 

    
    window.addEventListener("storage", checkLogin);
    return () => window.removeEventListener("storage", checkLogin);
  }, []);

  
  const handleLogout = () => {
    localStorage.removeItem("usuario");
    setIsLoggedIn(false);
    window.dispatchEvent(new Event("storage")); // atualiza o Header
    setMenuOpen(false);
  };

  return (
    <header className={styles.header}>
      {/* Logo */}
      <Link to="/" className={styles.logo} onClick={() => setMenuOpen(false)}>
        <SiHiveBlockchain className={styles.logoIcon} />
        &lt; zeroScam &gt;
      </Link>

     
      <button
        className={styles.hamburger}
        onClick={() => setMenuOpen(!menuOpen)}
        aria-label="Abrir menu"
      >
        {menuOpen ? <FaTimes /> : <FaBars />}
      </button>

     
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

        
        {isLoggedIn ? (
          <button 
            onClick={handleLogout}
            className={`${styles.sairBtn} ${styles.logoutBtn}`}
          >
            SAIR
          </button>
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
