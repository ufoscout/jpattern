package com.jpattern.ioc.model.xml;

import java.util.Map;

public class BeanUno {
        
        private String nome;
        private String cognome;
        
        private Residenza residenza;
        
        private String attributereplaced;
        
        private String attributereplacedcrypted;
        
        private Map<?,?> maptelefoni;
        
        private Map<?,?> mapmezzi;
        
        public BeanUno() {
        	
        }
        
        public BeanUno(String nome, String cognome){
        	setNome(nome);
        	setCognome(cognome);
        }
        
        public BeanUno(String nome, String cognome, Map<?, ?> mapmezzi){
        	setNome(nome);
        	setCognome(cognome);
        	setMapmezzi(mapmezzi);
        }

        public BeanUno(String nome, String cognome, Residenza residenza){
        	setNome(nome);
        	setCognome(cognome);
        	setResidenza(residenza);
        }
        
        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public Map<?, ?> getMapmezzi() {
            return mapmezzi;
        }

        public void setMapmezzi(Map<?, ?> mapmezzi) {
            this.mapmezzi = mapmezzi;
        }

        public Map<?, ?> getMaptelefoni() {
            return maptelefoni;
        }

        public void setMaptelefoni(Map<?, ?> maptelefoni) {
            this.maptelefoni = maptelefoni;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Residenza getResidenza() {
            return residenza;
        }

        public void setResidenza(Residenza residenza) {
            this.residenza = residenza;
        }

        public String getAttributereplaced() {
            return attributereplaced;
        }

        public void setAttributereplaced(String attributereplaced) {
            this.attributereplaced = attributereplaced;
        }

        public String getAttributereplacedcrypted() {
            return attributereplacedcrypted;
        }

        public void setAttributereplacedcrypted(String attributereplacedcrypted) {
            this.attributereplacedcrypted = attributereplacedcrypted;
        }
}
