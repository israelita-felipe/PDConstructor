Narrative:
Eu como supervisor
Desejo ter a opção de criar bases para serem classificados no modo seleção de elementos,
onde não é preciso ter classes para escolher.

Scenario: Criação de base de video com todos os campos preenchidos
 
Given Um supervisor
When Ele quiser criar uma base de video com titulo <titulo>, descricao <descricao> e arquivo <arquivo>
Then retorne a mensagem de criacao da base <mensagem>

Examples:
|titulo|descricao|arquivo|mensagem|
|teste01|descricao01|/home/bruno/git/PDConstructor/videos/video01.mp4|Base criada com sucesso|
|teste02|descricao02|/home/bruno/git/PDConstructor/videos/video02.mp4|Base criada com sucesso|
|teste03|descricao03|/home/bruno/git/PDConstructor/videos/video03.mp4|Base criada com sucesso|
|teste04|descricao04|/home/bruno/git/PDConstructor/videos/video04.mp4|Base criada com sucesso|
|teste05|descricao05|/home/bruno/git/PDConstructor/videos/video04.mp4|Base criada com sucesso|
|teste06|descricao06|/home/bruno/git/PDConstructor/videos/video05.mp4|Base criada com sucesso|
|teste07|descricao07|/home/bruno/git/PDConstructor/videos/video06.mp4|Base criada com sucesso|
|teste08|descricao08|/home/bruno/git/PDConstructor/videos/video07.mp4|Base criada com sucesso|
|teste09|descricao09|/home/bruno/git/PDConstructor/videos/video08.mp4|Base criada com sucesso|
|teste10|descricao10|/home/bruno/git/PDConstructor/videos/video09.mp4|Base criada com sucesso|
 
Scenario: Criação de base de videos faltando o titulo
 
Given Um supervisor
When Ele quiser criar uma base de video com titulo <titulo> vazio, descricao <descricao> e arquivo <arquivo>
Then retorne a mensagem de criacao da base <mensagem>

Examples:
|titulo|descricao|arquivo|mensagem|
| |descricao11|/home/bruno/git/PDConstructor/videos/video10.mp4|Erro|
| |descricao12|/home/bruno/git/PDConstructor/videos/video11.mp4|Erro|
| |descricao13|/home/bruno/git/PDConstructor/videos/video12.mp4|Erro|
| |descricao14|/home/bruno/git/PDConstructor/videos/video13.mp4|Erro|
| |descricao15|/home/bruno/git/PDConstructor/videos/video14.mp4|Erro|
 
 
Scenario: Criação de base faltando a descrição

Given Um supervisor
When Ele quiser criar uma base de video com titulo <titulo>, descricao <descricao> vazia e arquivo <arquivo>
Then retorne a mensagem de criacao da base <mensagem>

Examples:
|titulo|descricao|arquivo|mensagem|
|teste16| |/home/bruno/git/PDConstructor/videos/video14.mp4|Erro|
|teste17| |/home/bruno/git/PDConstructor/videos/video03.mp4|Erro|
|teste18| |/home/bruno/git/PDConstructor/videos/video04.mp4|Erro|
|teste19| |/home/bruno/git/PDConstructor/videos/video05.mp4|Erro|
|teste20| |/home/bruno/git/PDConstructor/videos/video09.mp4|Erro|
