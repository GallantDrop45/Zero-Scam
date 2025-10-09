import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Header.module.css';

export default function TopBar({ isLoggedIn }) {
  return (
    <div className={styles.topBar}>
      <div className={styles.topRight}>
        {isLoggedIn ? (
          <>
            <Link to="/conta">MINHA CONTA</Link>
            <Link to="/logout">SAIR</Link>
          </>
        ) : (
          <>
            <Link to="/login">LOGIN</Link>
            <Link to="/cadastro">CADASTRO</Link>
          </>
        )}
      </div>
    </div>
  );
}