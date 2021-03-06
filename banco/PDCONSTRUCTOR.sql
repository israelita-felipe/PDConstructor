-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.1
-- PostgreSQL version: 9.4
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: "PDCONSTRUCTOR" | type: DATABASE --
-- -- DROP DATABASE IF EXISTS "PDCONSTRUCTOR";
-- CREATE DATABASE "PDCONSTRUCTOR"
-- 	TEMPLATE = pdconstructor
-- ;
-- -- ddl-end --
-- 

-- object: public."PERFIL" | type: TABLE --
-- DROP TABLE IF EXISTS public."PERFIL" CASCADE;
CREATE TABLE public."PERFIL"(
	"NOME" varchar(100) NOT NULL,
	"DESCRICAO" text NOT NULL,
	CONSTRAINT "PERFIL_PK" PRIMARY KEY ("NOME")

);
-- ddl-end --
ALTER TABLE public."PERFIL" OWNER TO postgres;
-- ddl-end --

-- object: public."USUARIO" | type: TABLE --
-- DROP TABLE IF EXISTS public."USUARIO" CASCADE;
CREATE TABLE public."USUARIO"(
	"NOME" varchar(100),
	"EMAIL" text NOT NULL,
	"SENHA" text NOT NULL,
	"ID" serial NOT NULL,
	"PERFIL" varchar(100) NOT NULL,
	CONSTRAINT "USUARIO_PK" PRIMARY KEY ("ID"),
	CONSTRAINT "USUARIO_UK" UNIQUE ("EMAIL","PERFIL")

);
-- ddl-end --
ALTER TABLE public."USUARIO" OWNER TO postgres;
-- ddl-end --

-- object: public."BASE_TEXTO" | type: TABLE --
-- DROP TABLE IF EXISTS public."BASE_TEXTO" CASCADE;
CREATE TABLE public."BASE_TEXTO"(
	"ID" serial NOT NULL,
	"TITULO" text NOT NULL,
	"DESCRICAO" text NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "BASE_TEXTO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."BASE_TEXTO" OWNER TO postgres;
-- ddl-end --

-- object: public."ALOCACAO_TEXTO" | type: TABLE --
-- DROP TABLE IF EXISTS public."ALOCACAO_TEXTO" CASCADE;
CREATE TABLE public."ALOCACAO_TEXTO"(
	"BASE_TEXTO" integer,
	"TEXTO" text,
	"SUPERVISOR" integer,
	"ID" serial NOT NULL,
	CONSTRAINT "ALOCACAO_TEXTO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."ALOCACAO_TEXTO" OWNER TO postgres;
-- ddl-end --

-- object: public."ESCOLHA_CLASSE_TEXTO" | type: TABLE --
-- DROP TABLE IF EXISTS public."ESCOLHA_CLASSE_TEXTO" CASCADE;
CREATE TABLE public."ESCOLHA_CLASSE_TEXTO"(
	"ID" serial NOT NULL,
	"DESCRICAO" varchar(200) NOT NULL,
	"ALOCACAO_TEXTO" integer NOT NULL,
	CONSTRAINT "ESCOLHA_TEXTO" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."ESCOLHA_CLASSE_TEXTO" OWNER TO postgres;
-- ddl-end --

-- object: public."CLASSIFICACAO_TEXTO" | type: TABLE --
-- DROP TABLE IF EXISTS public."CLASSIFICACAO_TEXTO" CASCADE;
CREATE TABLE public."CLASSIFICACAO_TEXTO"(
	"ESCRAVO" integer NOT NULL,
	"ALOCACAO_TEXTO" integer NOT NULL,
	"ESCOLHA_TEXTO" integer NOT NULL,
	"ID" serial NOT NULL,
	CONSTRAINT "CLASSIFICACAO_TEXTO_PK" PRIMARY KEY ("ID","ESCRAVO","ALOCACAO_TEXTO","ESCOLHA_TEXTO")

);
-- ddl-end --
ALTER TABLE public."CLASSIFICACAO_TEXTO" OWNER TO postgres;
-- ddl-end --

-- object: public."LIBERACAO_BASE_TEXTO" | type: TABLE --
-- DROP TABLE IF EXISTS public."LIBERACAO_BASE_TEXTO" CASCADE;
CREATE TABLE public."LIBERACAO_BASE_TEXTO"(
	"BASE_TEXTO" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	"ESCRAVO" integer NOT NULL,
	"STATUS" char NOT NULL,
	CONSTRAINT "LIBERACAO_BASE_TEXTO_PK" PRIMARY KEY ("BASE_TEXTO","SUPERVISOR","ESCRAVO")

);
-- ddl-end --
ALTER TABLE public."LIBERACAO_BASE_TEXTO" OWNER TO postgres;
-- ddl-end --

-- object: public."BASE_IMAGEM_CLASSE" | type: TABLE --
-- DROP TABLE IF EXISTS public."BASE_IMAGEM_CLASSE" CASCADE;
CREATE TABLE public."BASE_IMAGEM_CLASSE"(
	"ID" serial NOT NULL,
	"TITULO" text NOT NULL,
	"DESCRICAO" text NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "BASE_IMAGEM_CLASSE_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."BASE_IMAGEM_CLASSE" OWNER TO postgres;
-- ddl-end --

-- object: public."IMAGEM_CLASSE" | type: TABLE --
-- DROP TABLE IF EXISTS public."IMAGEM_CLASSE" CASCADE;
CREATE TABLE public."IMAGEM_CLASSE"(
	"ID" serial NOT NULL,
	"OBJETO" bytea NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "IMAGEM_CLASSE_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."IMAGEM_CLASSE" OWNER TO postgres;
-- ddl-end --

-- object: public."ALOCACAO_IMAGEM_CLASSE" | type: TABLE --
-- DROP TABLE IF EXISTS public."ALOCACAO_IMAGEM_CLASSE" CASCADE;
CREATE TABLE public."ALOCACAO_IMAGEM_CLASSE"(
	"ID" serial NOT NULL,
	"BASE_IMAGEM_CLASSE" integer NOT NULL,
	"IMAGEM_CLASSE" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "ALOCACAO_IMAGEM_CLASSE_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" OWNER TO postgres;
-- ddl-end --

-- object: public."ESCOLHA_IMAGEM_CLASSE" | type: TABLE --
-- DROP TABLE IF EXISTS public."ESCOLHA_IMAGEM_CLASSE" CASCADE;
CREATE TABLE public."ESCOLHA_IMAGEM_CLASSE"(
	"ID" serial NOT NULL,
	"DESCRICAO" text NOT NULL,
	"ALOCACAO_IMAGEM_CLASSE" integer NOT NULL,
	CONSTRAINT "ESCOLHA_IMAGEM_CLASSE_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."ESCOLHA_IMAGEM_CLASSE" OWNER TO postgres;
-- ddl-end --

-- object: public."CLASSSIFICACAO_IMAGEM_CLASSE" | type: TABLE --
-- DROP TABLE IF EXISTS public."CLASSSIFICACAO_IMAGEM_CLASSE" CASCADE;
CREATE TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE"(
	"ESCRAVO" integer NOT NULL,
	"ALOCACAO_IMAGEM_CLASSE" integer NOT NULL,
	"ESCOLHA_IMAGEM_CLASSE" integer NOT NULL,
	"ID" serial NOT NULL,
	CONSTRAINT "CLASSIFICACAO_IMAGEM_CLASSE_PK" PRIMARY KEY ("ESCRAVO","ALOCACAO_IMAGEM_CLASSE","ESCOLHA_IMAGEM_CLASSE","ID")

);
-- ddl-end --
ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" OWNER TO postgres;
-- ddl-end --

-- object: public."LIBERACAO_BASE_IMAGEM_CLASSE" | type: TABLE --
-- DROP TABLE IF EXISTS public."LIBERACAO_BASE_IMAGEM_CLASSE" CASCADE;
CREATE TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE"(
	"BASE_IMAGEM" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	"ESCRAVO" integer NOT NULL,
	"STATUS" char NOT NULL,
	CONSTRAINT "LIBERACAO_BASE_IMAGEM_CLASSE_PK" PRIMARY KEY ("BASE_IMAGEM","SUPERVISOR","ESCRAVO")

);
-- ddl-end --
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" OWNER TO postgres;
-- ddl-end --

-- object: public."BASE_IMAGEM_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."BASE_IMAGEM_DETECCAO" CASCADE;
CREATE TABLE public."BASE_IMAGEM_DETECCAO"(
	"ID" serial NOT NULL,
	"TITULO" text NOT NULL,
	"DESCRICAO" text NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "BASE_IMAGEM_DETECCAO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."BASE_IMAGEM_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."LIBERACAO_BASE_IMAGEM_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."LIBERACAO_BASE_IMAGEM_DETECCAO" CASCADE;
CREATE TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO"(
	"ESCRAVO" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	"BASE_IMAGEM_DETECCAO" integer NOT NULL,
	"STATUS" char NOT NULL,
	CONSTRAINT "LIBERACAO_BASE_IMAGEM_DETECCAO_PK" PRIMARY KEY ("BASE_IMAGEM_DETECCAO","SUPERVISOR","ESCRAVO")

);
-- ddl-end --
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."IMAGEM_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."IMAGEM_DETECCAO" CASCADE;
CREATE TABLE public."IMAGEM_DETECCAO"(
	"ID" serial NOT NULL,
	"OBJETO" bytea NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "IMAGEM_DETECCAO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."IMAGEM_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."ALOCACAO_IMAGEM_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."ALOCACAO_IMAGEM_DETECCAO" CASCADE;
CREATE TABLE public."ALOCACAO_IMAGEM_DETECCAO"(
	"ID" serial NOT NULL,
	"BASE_IMAGEM_DETECCAO" integer NOT NULL,
	"IMAGEM_DETECCAO" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "ALOCACAO_IMAGEM_DETECCAO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."DETECCAO_IMAGEM" | type: TABLE --
-- DROP TABLE IF EXISTS public."DETECCAO_IMAGEM" CASCADE;
CREATE TABLE public."DETECCAO_IMAGEM"(
	"X1" double precision NOT NULL,
	"Y1" double precision NOT NULL,
	"X2" double precision NOT NULL,
	"Y2" double precision NOT NULL,
	"ALOCACAO_IMAGEM_DETECCCAO" integer NOT NULL,
	"ESCRAVO" integer NOT NULL,
	CONSTRAINT "DETECCAO_IMAGEM_PK" PRIMARY KEY ("X1","Y1","X2","Y2","ALOCACAO_IMAGEM_DETECCCAO","ESCRAVO")

);
-- ddl-end --
ALTER TABLE public."DETECCAO_IMAGEM" OWNER TO postgres;
-- ddl-end --

-- object: public."VIDEO_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."VIDEO_DETECCAO" CASCADE;
CREATE TABLE public."VIDEO_DETECCAO"(
	"ID" serial NOT NULL,
	"OBJETO" bytea NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "VIDEO_DETECCAO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."VIDEO_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."BASE_VIDEO_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."BASE_VIDEO_DETECCAO" CASCADE;
CREATE TABLE public."BASE_VIDEO_DETECCAO"(
	"ID" serial NOT NULL,
	"TITULO" text NOT NULL,
	"DESCRICAO" text NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "BASE_VIDEO_DETECCAO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."BASE_VIDEO_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."ALOCACAO_VIDEO_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."ALOCACAO_VIDEO_DETECCAO" CASCADE;
CREATE TABLE public."ALOCACAO_VIDEO_DETECCAO"(
	"ID" serial NOT NULL,
	"BASE_VIDEO_DETECCAO" integer NOT NULL,
	"VIDEO_DETECCAO" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	CONSTRAINT "ALOCACAO_VIDEO_DETECCAO_PK" PRIMARY KEY ("ID")

);
-- ddl-end --
ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."LIBERACAO_BASE_VIDEO_DETECCAO" | type: TABLE --
-- DROP TABLE IF EXISTS public."LIBERACAO_BASE_VIDEO_DETECCAO" CASCADE;
CREATE TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO"(
	"ESCRAVO" integer NOT NULL,
	"SUPERVISOR" integer NOT NULL,
	"BASE_VIDEO_DETECCAO" integer NOT NULL,
	"STATUS" char NOT NULL,
	CONSTRAINT "LIBERACAO_BASE_VIDEO_DETECCAO_PK" PRIMARY KEY ("ESCRAVO","SUPERVISOR","BASE_VIDEO_DETECCAO")

);
-- ddl-end --
ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" OWNER TO postgres;
-- ddl-end --

-- object: public."DETECCAO_VIDEO" | type: TABLE --
-- DROP TABLE IF EXISTS public."DETECCAO_VIDEO" CASCADE;
CREATE TABLE public."DETECCAO_VIDEO"(
	"X1" double precision NOT NULL,
	"X2" double precision NOT NULL,
	"Y1" double precision NOT NULL,
	"Y2" double precision NOT NULL,
	"ALOCACAO_VIDEO_DETECCAO" integer NOT NULL,
	"ESCRAVO" integer NOT NULL,
	"TEMPO" double precision NOT NULL,
	CONSTRAINT "DETECCAO_VIDEO_PK" PRIMARY KEY ("X1","X2","Y1","Y2","ALOCACAO_VIDEO_DETECCAO","ESCRAVO","TEMPO")

);
-- ddl-end --
ALTER TABLE public."DETECCAO_VIDEO" OWNER TO postgres;
-- ddl-end --

-- object: public.base_texto_histograma | type: VIEW --
-- DROP VIEW IF EXISTS public.base_texto_histograma CASCADE;
CREATE VIEW public.base_texto_histograma
AS 

SELECT
   distinct
	escolha_classe_texto.descricao AS classe,
	base_texto.*,

	(select count(*)
		FROM 
		  public.classificacao_texto, 
		  public.base_texto, 
		  public.alocacao_texto
		WHERE 
		  escolha_classe_texto.descricao = (select escolha_classe_texto.descricao from escolha_classe_texto where classificacao_texto.escolha_texto = escolha_classe_texto.id ) 		  AND
		  alocacao_texto.base_texto = base_texto.id AND
		  alocacao_texto.id = classificacao_texto.alocacao_texto
	) as total
	
FROM
   public.escolha_classe_texto,
	public.base_texto,
	public.alocacao_texto
WHERE
   public.escolha_classe_texto.alocacao_texto = alocacao_texto.id
	AND
	public.alocacao_texto.base_texto = base_texto.id
ORDER BY 
	public.base_texto.id;
-- ddl-end --
ALTER VIEW public.base_texto_histograma OWNER TO postgres;
-- ddl-end --

-- object: public.base_imagem_classe_histograma | type: VIEW --
-- DROP VIEW IF EXISTS public.base_imagem_classe_histograma CASCADE;
CREATE VIEW public.base_imagem_classe_histograma
AS 

SELECT
   distinct
	escolha_imagem_classe.descricao AS classe,
	base_imagem_classe.*,

	(select count(*)
		FROM 
		  public.classsificacao_imagem_classe, 
		  public.base_imagem_classe, 
		  public.alocacao_imagem_classe
		WHERE 
		  escolha_imagem_classe.descricao = (select escolha_imagem_classe.descricao from escolha_imagem_classe where classsificacao_imagem_classe.escolha_imagem_classe = escolha_imagem_classe.id ) 		  AND
		  alocacao_imagem_classe.base_imagem_classe = base_imagem_classe.id AND
		  alocacao_imagem_classe.id = classsificacao_imagem_classe.alocacao_imagem_classe
	) as total
	
FROM
   public.escolha_imagem_classe,
	public.base_imagem_classe,
	public.alocacao_imagem_classe
WHERE
   public.escolha_imagem_classe.alocacao_imagem_classe = alocacao_imagem_classe.id
	AND
	public.alocacao_imagem_classe.base_imagem_classe = base_imagem_classe.id
ORDER BY 
	public.base_imagem_classe.id;
-- ddl-end --
ALTER VIEW public.base_imagem_classe_histograma OWNER TO postgres;
-- ddl-end --

-- object: public.base_imagem_deteccao_histograma | type: VIEW --
-- DROP VIEW IF EXISTS public.base_imagem_deteccao_histograma CASCADE;
CREATE VIEW public.base_imagem_deteccao_histograma
AS 

select distinct imagem_deteccao.id,imagem_deteccao.objeto,base_imagem_deteccao.id as base,
(select count(*)

from	
	alocacao_imagem_deteccao,
	deteccao_imagem
where
	deteccao_imagem.alocacao_imagem_detecccao = alocacao_imagem_deteccao.id AND
	imagem_deteccao.id = alocacao_imagem_deteccao.imagem_deteccao 
	

) as total

from 
	imagem_deteccao,deteccao_imagem,base_imagem_deteccao,alocacao_imagem_deteccao
where 
	alocacao_imagem_deteccao.base_imagem_deteccao = base_imagem_deteccao.id AND	
	imagem_deteccao.id = alocacao_imagem_deteccao.imagem_deteccao 
order by
	imagem_deteccao.id;
-- ddl-end --
ALTER VIEW public.base_imagem_deteccao_histograma OWNER TO postgres;
-- ddl-end --

-- object: public.base_video_deteccao_histograma | type: VIEW --
-- DROP VIEW IF EXISTS public.base_video_deteccao_histograma CASCADE;
CREATE VIEW public.base_video_deteccao_histograma
AS 

SELECT DISTINCT video_deteccao.id,
    video_deteccao.objeto,
    base_video_deteccao.id AS base,
    ( SELECT count(*) AS count
           FROM alocacao_video_deteccao,
            deteccao_video
          WHERE deteccao_video.alocacao_video_deteccao = alocacao_video_deteccao.id AND video_deteccao.id = alocacao_video_deteccao.video_deteccao) AS total
   FROM video_deteccao,
    deteccao_video,
    base_video_deteccao,
    alocacao_video_deteccao
  WHERE alocacao_video_deteccao.base_video_deteccao = base_video_deteccao.id AND video_deteccao.id = alocacao_video_deteccao.video_deteccao
  ORDER BY video_deteccao.id;;
-- ddl-end --
ALTER VIEW public.base_video_deteccao_histograma OWNER TO postgres;
-- ddl-end --

-- object: "USUARIO_PERFIL_FK" | type: CONSTRAINT --
-- ALTER TABLE public."USUARIO" DROP CONSTRAINT IF EXISTS "USUARIO_PERFIL_FK" CASCADE;
ALTER TABLE public."USUARIO" ADD CONSTRAINT "USUARIO_PERFIL_FK" FOREIGN KEY ("PERFIL")
REFERENCES public."PERFIL" ("NOME") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "BASE_TEXTO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."BASE_TEXTO" DROP CONSTRAINT IF EXISTS "BASE_TEXTO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."BASE_TEXTO" ADD CONSTRAINT "BASE_TEXTO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_TEXTO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_TEXTO" DROP CONSTRAINT IF EXISTS "ALOCACAO_TEXTO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."ALOCACAO_TEXTO" ADD CONSTRAINT "ALOCACAO_TEXTO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_TEXTO_BASE_TEXTO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_TEXTO" DROP CONSTRAINT IF EXISTS "ALOCACAO_TEXTO_BASE_TEXTO_FK" CASCADE;
ALTER TABLE public."ALOCACAO_TEXTO" ADD CONSTRAINT "ALOCACAO_TEXTO_BASE_TEXTO_FK" FOREIGN KEY ("BASE_TEXTO")
REFERENCES public."BASE_TEXTO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ESCOLHA_TEXTO_ALOCACAO_TEXTO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ESCOLHA_CLASSE_TEXTO" DROP CONSTRAINT IF EXISTS "ESCOLHA_TEXTO_ALOCACAO_TEXTO_FK" CASCADE;
ALTER TABLE public."ESCOLHA_CLASSE_TEXTO" ADD CONSTRAINT "ESCOLHA_TEXTO_ALOCACAO_TEXTO_FK" FOREIGN KEY ("ALOCACAO_TEXTO")
REFERENCES public."ALOCACAO_TEXTO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "CLASSIFICACAO_TEXTO_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."CLASSIFICACAO_TEXTO" DROP CONSTRAINT IF EXISTS "CLASSIFICACAO_TEXTO_ESCRAVO_FK" CASCADE;
ALTER TABLE public."CLASSIFICACAO_TEXTO" ADD CONSTRAINT "CLASSIFICACAO_TEXTO_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "CLASSIFICACAO_TEXTO_ALOCACAO_TEXTO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."CLASSIFICACAO_TEXTO" DROP CONSTRAINT IF EXISTS "CLASSIFICACAO_TEXTO_ALOCACAO_TEXTO_FK" CASCADE;
ALTER TABLE public."CLASSIFICACAO_TEXTO" ADD CONSTRAINT "CLASSIFICACAO_TEXTO_ALOCACAO_TEXTO_FK" FOREIGN KEY ("ALOCACAO_TEXTO")
REFERENCES public."ALOCACAO_TEXTO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "CLASSIFICACAO_TEXTO_ESCOLHA_TEXTO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."CLASSIFICACAO_TEXTO" DROP CONSTRAINT IF EXISTS "CLASSIFICACAO_TEXTO_ESCOLHA_TEXTO_FK" CASCADE;
ALTER TABLE public."CLASSIFICACAO_TEXTO" ADD CONSTRAINT "CLASSIFICACAO_TEXTO_ESCOLHA_TEXTO_FK" FOREIGN KEY ("ESCOLHA_TEXTO")
REFERENCES public."ESCOLHA_CLASSE_TEXTO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_TEXTO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_TEXTO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_TEXTO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_TEXTO" ADD CONSTRAINT "LIBERACAO_BASE_TEXTO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_TEXTO_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_TEXTO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_TEXTO_ESCRAVO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_TEXTO" ADD CONSTRAINT "LIBERACAO_BASE_TEXTO_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_TEXTO_BASE_TEXTO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_TEXTO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_TEXTO_BASE_TEXTO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_TEXTO" ADD CONSTRAINT "LIBERACAO_BASE_TEXTO_BASE_TEXTO_FK" FOREIGN KEY ("BASE_TEXTO")
REFERENCES public."BASE_TEXTO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "BASE_IMAGEM_CLASSE_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."BASE_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "BASE_IMAGEM_CLASSE_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."BASE_IMAGEM_CLASSE" ADD CONSTRAINT "BASE_IMAGEM_CLASSE_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "IMAGEM_CLASSE_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "IMAGEM_CLASSE_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."IMAGEM_CLASSE" ADD CONSTRAINT "IMAGEM_CLASSE_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_IMAGEM_CLASSE_BASE_IMAGEM_CLASSE_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "ALOCACAO_IMAGEM_CLASSE_BASE_IMAGEM_CLASSE_FK" CASCADE;
ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" ADD CONSTRAINT "ALOCACAO_IMAGEM_CLASSE_BASE_IMAGEM_CLASSE_FK" FOREIGN KEY ("BASE_IMAGEM_CLASSE")
REFERENCES public."BASE_IMAGEM_CLASSE" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_IMAGEM_CLASSE_IMAGEM_CLASSE_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "ALOCACAO_IMAGEM_CLASSE_IMAGEM_CLASSE_FK" CASCADE;
ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" ADD CONSTRAINT "ALOCACAO_IMAGEM_CLASSE_IMAGEM_CLASSE_FK" FOREIGN KEY ("IMAGEM_CLASSE")
REFERENCES public."IMAGEM_CLASSE" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_IMAGEM_CLASSE_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "ALOCACAO_IMAGEM_CLASSE_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."ALOCACAO_IMAGEM_CLASSE" ADD CONSTRAINT "ALOCACAO_IMAGEM_CLASSE_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ESCOLHA_IMAGEM_CLASSE_ALOCACAO_IMAGEM_CLASSE_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ESCOLHA_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "ESCOLHA_IMAGEM_CLASSE_ALOCACAO_IMAGEM_CLASSE_FK" CASCADE;
ALTER TABLE public."ESCOLHA_IMAGEM_CLASSE" ADD CONSTRAINT "ESCOLHA_IMAGEM_CLASSE_ALOCACAO_IMAGEM_CLASSE_FK" FOREIGN KEY ("ALOCACAO_IMAGEM_CLASSE")
REFERENCES public."ALOCACAO_IMAGEM_CLASSE" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "CLASSIFICACAO_IMAGEM_CLASSE_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "CLASSIFICACAO_IMAGEM_CLASSE_ESCRAVO_FK" CASCADE;
ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" ADD CONSTRAINT "CLASSIFICACAO_IMAGEM_CLASSE_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "CLASSIFICACAO_IMAGEM_CLASSE_ALOCACAO_IMAGEM_CLASSE_FK" | type: CONSTRAINT --
-- ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "CLASSIFICACAO_IMAGEM_CLASSE_ALOCACAO_IMAGEM_CLASSE_FK" CASCADE;
ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" ADD CONSTRAINT "CLASSIFICACAO_IMAGEM_CLASSE_ALOCACAO_IMAGEM_CLASSE_FK" FOREIGN KEY ("ALOCACAO_IMAGEM_CLASSE")
REFERENCES public."ALOCACAO_IMAGEM_CLASSE" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "CLASSIFICACAO_IMAGEM_CLASSE_ESCOLHA_IMAGEM_CLASSE_FK" | type: CONSTRAINT --
-- ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "CLASSIFICACAO_IMAGEM_CLASSE_ESCOLHA_IMAGEM_CLASSE_FK" CASCADE;
ALTER TABLE public."CLASSSIFICACAO_IMAGEM_CLASSE" ADD CONSTRAINT "CLASSIFICACAO_IMAGEM_CLASSE_ESCOLHA_IMAGEM_CLASSE_FK" FOREIGN KEY ("ESCOLHA_IMAGEM_CLASSE")
REFERENCES public."ESCOLHA_IMAGEM_CLASSE" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_IMAGEM_CLASSE_BASE_IMAGEM_CLASSE" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_IMAGEM_CLASSE_BASE_IMAGEM_CLASSE" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" ADD CONSTRAINT "LIBERACAO_BASE_IMAGEM_CLASSE_BASE_IMAGEM_CLASSE" FOREIGN KEY ("BASE_IMAGEM")
REFERENCES public."BASE_IMAGEM_CLASSE" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_IMAGEM_CLASSE_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_IMAGEM_CLASSE_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" ADD CONSTRAINT "LIBERACAO_BASE_IMAGEM_CLASSE_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_IMAGEM_CLASSE_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_IMAGEM_CLASSE_ESCRAVO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_CLASSE" ADD CONSTRAINT "LIBERACAO_BASE_IMAGEM_CLASSE_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "BASE_IMAGEM_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."BASE_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "BASE_IMAGEM_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."BASE_IMAGEM_DETECCAO" ADD CONSTRAINT "BASE_IMAGEM_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_IMAGEM_DETECCAO_BASE_IMAGEM_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_IMAGEM_DETECCAO_BASE_IMAGEM_DETECCAO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" ADD CONSTRAINT "LIBERACAO_BASE_IMAGEM_DETECCAO_BASE_IMAGEM_DETECCAO_FK" FOREIGN KEY ("BASE_IMAGEM_DETECCAO")
REFERENCES public."BASE_IMAGEM_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "LIBERACAO_BASE_IMAGEM_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_IMAGEM_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" ADD CONSTRAINT "LIBERACAO_BASE_IMAGEM_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_IMAGEM_DETECCAO_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_IMAGEM_DETECCAO_ESCRAVO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_IMAGEM_DETECCAO" ADD CONSTRAINT "LIBERACAO_BASE_IMAGEM_DETECCAO_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "IMAGEM_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "IMAGEM_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."IMAGEM_DETECCAO" ADD CONSTRAINT "IMAGEM_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_IMAGEM_DETECCAO_BASE_IMAGEM_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "ALOCACAO_IMAGEM_DETECCAO_BASE_IMAGEM_DETECCAO_FK" CASCADE;
ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" ADD CONSTRAINT "ALOCACAO_IMAGEM_DETECCAO_BASE_IMAGEM_DETECCAO_FK" FOREIGN KEY ("BASE_IMAGEM_DETECCAO")
REFERENCES public."BASE_IMAGEM_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_IMAGEM_DETECCAO_IMAGEM_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "ALOCACAO_IMAGEM_DETECCAO_IMAGEM_DETECCAO_FK" CASCADE;
ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" ADD CONSTRAINT "ALOCACAO_IMAGEM_DETECCAO_IMAGEM_DETECCAO_FK" FOREIGN KEY ("IMAGEM_DETECCAO")
REFERENCES public."IMAGEM_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_IMAGEM_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" DROP CONSTRAINT IF EXISTS "ALOCACAO_IMAGEM_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."ALOCACAO_IMAGEM_DETECCAO" ADD CONSTRAINT "ALOCACAO_IMAGEM_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "DETECCAO_IMAGEM_ALOCACAO_IMAGEM_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."DETECCAO_IMAGEM" DROP CONSTRAINT IF EXISTS "DETECCAO_IMAGEM_ALOCACAO_IMAGEM_DETECCAO_FK" CASCADE;
ALTER TABLE public."DETECCAO_IMAGEM" ADD CONSTRAINT "DETECCAO_IMAGEM_ALOCACAO_IMAGEM_DETECCAO_FK" FOREIGN KEY ("ALOCACAO_IMAGEM_DETECCCAO")
REFERENCES public."ALOCACAO_IMAGEM_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: "DETECCAO_IMAGEM_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."DETECCAO_IMAGEM" DROP CONSTRAINT IF EXISTS "DETECCAO_IMAGEM_ESCRAVO_FK" CASCADE;
ALTER TABLE public."DETECCAO_IMAGEM" ADD CONSTRAINT "DETECCAO_IMAGEM_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "VIDEO_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "VIDEO_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."VIDEO_DETECCAO" ADD CONSTRAINT "VIDEO_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "BASE_VIDEO_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."BASE_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "BASE_VIDEO_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."BASE_VIDEO_DETECCAO" ADD CONSTRAINT "BASE_VIDEO_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "ALOCACAO_VIDEO_DETECCAO_BASE_VIDEO_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "ALOCACAO_VIDEO_DETECCAO_BASE_VIDEO_DETECCAO_FK" CASCADE;
ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" ADD CONSTRAINT "ALOCACAO_VIDEO_DETECCAO_BASE_VIDEO_DETECCAO_FK" FOREIGN KEY ("BASE_VIDEO_DETECCAO")
REFERENCES public."BASE_VIDEO_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "ALOCACAO_VIDEO_DETECCAO_VIDEO_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "ALOCACAO_VIDEO_DETECCAO_VIDEO_DETECCAO_FK" CASCADE;
ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" ADD CONSTRAINT "ALOCACAO_VIDEO_DETECCAO_VIDEO_DETECCAO_FK" FOREIGN KEY ("VIDEO_DETECCAO")
REFERENCES public."VIDEO_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "ALOCACAO_VIDEO_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "ALOCACAO_VIDEO_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."ALOCACAO_VIDEO_DETECCAO" ADD CONSTRAINT "ALOCACAO_VIDEO_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE RESTRICT;
-- ddl-end --

-- object: "LIBERACAO_BASE_VIDEO_DETECCAO_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_VIDEO_DETECCAO_ESCRAVO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" ADD CONSTRAINT "LIBERACAO_BASE_VIDEO_DETECCAO_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_VIDEO_DETECCAO_SUPERVISOR_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_VIDEO_DETECCAO_SUPERVISOR_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" ADD CONSTRAINT "LIBERACAO_BASE_VIDEO_DETECCAO_SUPERVISOR_FK" FOREIGN KEY ("SUPERVISOR")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "LIBERACAO_BASE_VIDEO_DETECCAO_BASE_VIDEO_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" DROP CONSTRAINT IF EXISTS "LIBERACAO_BASE_VIDEO_DETECCAO_BASE_VIDEO_DETECCAO_FK" CASCADE;
ALTER TABLE public."LIBERACAO_BASE_VIDEO_DETECCAO" ADD CONSTRAINT "LIBERACAO_BASE_VIDEO_DETECCAO_BASE_VIDEO_DETECCAO_FK" FOREIGN KEY ("BASE_VIDEO_DETECCAO")
REFERENCES public."BASE_VIDEO_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "DETECCAO_VIDEO_ALOCACAO_VIDEO_DETECCAO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."DETECCAO_VIDEO" DROP CONSTRAINT IF EXISTS "DETECCAO_VIDEO_ALOCACAO_VIDEO_DETECCAO_FK" CASCADE;
ALTER TABLE public."DETECCAO_VIDEO" ADD CONSTRAINT "DETECCAO_VIDEO_ALOCACAO_VIDEO_DETECCAO_FK" FOREIGN KEY ("ALOCACAO_VIDEO_DETECCAO")
REFERENCES public."ALOCACAO_VIDEO_DETECCAO" ("ID") MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "DETECCAO_VIDEO_ESCRAVO_FK" | type: CONSTRAINT --
-- ALTER TABLE public."DETECCAO_VIDEO" DROP CONSTRAINT IF EXISTS "DETECCAO_VIDEO_ESCRAVO_FK" CASCADE;
ALTER TABLE public."DETECCAO_VIDEO" ADD CONSTRAINT "DETECCAO_VIDEO_ESCRAVO_FK" FOREIGN KEY ("ESCRAVO")
REFERENCES public."USUARIO" ("ID") MATCH SIMPLE
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --


