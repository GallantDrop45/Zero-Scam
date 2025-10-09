import React from 'react';
import Header from '../components/Header/Header';
import styles from './DenunciarGolpe.module.css';

export default function DenunciarGolpe() {
  return (
    <section className={styles.hero}>
      {/* Header global */}
      <Header />

      <div className={styles.heroContent}>
        <h1>Denunciar Link</h1>
      </div>
    </section>
  );
}