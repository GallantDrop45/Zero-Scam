import React from 'react';
import Header from '../components/Header/Header';
import styles from './VerificarLink.module.css';

export default function VerificarLink() {
  return (
    <section className={styles.hero}>
      {/* Header global */}
      <Header />

      <div className={styles.heroContent}>
        <h1>Verificar Link</h1>
        <p>Insira o link abaixo para verificar se ele é seguro.</p>
        {/* Aqui futuramente virá o campo de input e botão de verificação */}
      </div>
    </section>
  );
}