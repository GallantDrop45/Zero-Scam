import React, { useState } from 'react';
import Header from '../components/Header/Header';
import styles from './VerificarLink.module.css';
import RelatorioLink from "../components/RelatorioLink";

import { FaCheckCircle, FaExclamationTriangle, FaSpinner } from 'react-icons/fa';
import { Link } from 'react-router-dom';

export default function VerificarLink() {
  const [link, setLink] = useState('');
  const [resultado, setResultado] = useState(null);
  const [carregando, setCarregando] = useState(false);

  const isLoggedIn = Boolean(localStorage.getItem("usuario"));

  const handleVerificar = async () => {
    const urlRegex = /^(https?:\/\/)([a-z0-9-]+\.)+[a-z]{2,}(\/.*)?$/i;

    if (!link.trim()) {
      setResultado({
        status: 'erro',
        mensagem: 'Por favor, insira um link antes de analisar.',
      });
      return;
    }

    if (!urlRegex.test(link.trim())) {
      setResultado({
        status: 'erro',
        mensagem: 'O link inserido não parece válido.',
      });
      return;
    }

    setCarregando(true);
    setResultado(null);

    try {
      console.log('🔍 Verificando link:', link);

      
      const response = await fetch('http://localhost:8080/api/verificar-link', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ link: link.trim() })
      });

      const data = await response.json();
      console.log('Resposta da API:', data);

      if (data.sucesso) {
        const analise = data.dados;

        
        const ehPerigoso = analise.suspeito || analise.scoreRisco >= 60 || analise.totalDenuncias >= 5;

        setResultado({
          status: ehPerigoso ? 'perigoso' : 'seguro',
          mensagem: ehPerigoso 
            ? 'Detectamos atividades suspeitas associadas a este link.'
            : 'Este link parece confiável e não foi reportado em nossa base de dados.',
          detalhes: {
            dominioRegistrado: analise.dominioRegistrado,
            score: analise.scoreRisco,
            totalDenuncias: analise.totalDenuncias,
            valorColetado: analise.valorTotalPerdido,
            dicaSeguranca: analise.dicaSeguranca,
            paisRegistro: analise.paisRegistro,
            dataRegistro: analise.dataRegistro,
            dominio: analise.dominio
          },
        });
      } else {
        setResultado({
          status: 'erro',
          mensagem: data.mensagem || 'Erro ao analisar o link. Tente novamente.',
        });
      }

    } catch (error) {
      console.error('Erro ao verificar link:', error);
      setResultado({
        status: 'erro',
        mensagem: 'Erro ao conectar com o servidor. Verifique se o backend está rodando.',
      });
    } finally {
      setCarregando(false);
    }
  };

  return (
    <section className={styles.hero}>
      <Header />

      <div className={styles.verificarContainer}>
        <h1>Verificação Inteligente de Links</h1>
        <p>
          Nosso sistema analisa e classifica a segurança de URLs em tempo real.  
          Cole o link abaixo e descubra se ele é confiável.
        </p>

        <div className={styles.form}>
          <input
            type="text"
            placeholder="Ex: https://www.exemplo.com"
            value={link}
            onChange={(e) => setLink(e.target.value)}
            onKeyPress={(e) => {
              if (e.key === 'Enter') {
                handleVerificar();
              }
            }}
          />
          <button onClick={handleVerificar} disabled={carregando}>
            {carregando ? <FaSpinner className={styles.spin} /> : 'ANALISAR'}
          </button>
        </div>

        {/* RESULTADO */}
        {resultado && (
          <>
            <div
              className={`${styles.resultadoBox} ${
                resultado.status === 'seguro'
                  ? styles.seguro
                  : resultado.status === 'perigoso'
                  ? styles.perigoso
                  : styles.erro
              }`}
            >
              {resultado.status === 'seguro' && <FaCheckCircle className={styles.icon} />}
              {resultado.status === 'perigoso' && <FaExclamationTriangle className={styles.icon} />}
              <p>{resultado.mensagem}</p>
            </div>

            {!carregando && resultado.status !== 'erro' && (
              <>
                <hr className={styles.divisor} />

                {isLoggedIn ? (
                  <RelatorioLink detalhes={resultado.detalhes} />
                ) : (
                  <div className={styles.ctaBox}>
                    <h3>Relatório detalhado disponível</h3>
                    <p>
                      Faça login ou crie uma conta gratuita para acessar a análise completa deste link.
                    </p>
                    <div className={styles.ctaButtons}>
                      <Link to="/login" className={styles.btnSecundario}>
                        Fazer Login
                      </Link>
                      <Link to="/cadastro" className={styles.btnPrimario}>
                        Criar Conta
                      </Link>
                    </div>
                  </div>
                )}
              </>
            )}
          </>
        )}
      </div>
    </section>
  );
}