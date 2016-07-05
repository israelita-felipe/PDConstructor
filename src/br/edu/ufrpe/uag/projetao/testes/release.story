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
|teste01|descricao01|/videos/video01.mp4|Base criada com sucesso|
|teste02|descricao02|/videos/video02.mp4|Base criada com sucesso|
|teste03|descricao03|/videos/video03.mp4|Base criada com sucesso|
|teste04|descricao04|/videos/video04.mp4|Base criada com sucesso|
|teste05|descricao05|/videos/video04.mp4|Base criada com sucesso|
|teste06|descricao06|/videos/video05.mp4|Base criada com sucesso|
|teste07|descricao07|/videos/video06.mp4|Base criada com sucesso|
|teste08|descricao08|/videos/video07.mp4|Base criada com sucesso|
|teste09|descricao09|/videos/video08.mp4|Base criada com sucesso|
|teste10|descricao10|/videos/video09.mp4|Base criada com sucesso|
 
Scenario: Criação de base de videos faltando o titulo
 
Given Um supervisor
When Ele quiser criar uma base de video com titulo <titulo> vazio, descricao <descricao> e arquivo <arquivo>
Then retorne a mensagem de criacao da base <mensagem>

Examples:
|titulo|descricao|arquivo|mensagem|
| |descricao11|/videos/video10.mp4|Título deve ser preenchido|
| |descricao12|/videos/video11.mp4|Título deve ser preenchido|
| |descricao13|/videos/video12.mp4|Título deve ser preenchido|
| |descricao14|/videos/video13.mp4|Título deve ser preenchido|
| |descricao15|/videos/video14.mp4|Título deve ser preenchido|
 
 
Scenario: Criação de base faltando a descrição

Given Um supervisor
When Ele quiser criar uma base de video com titulo <titulo>, descricao <descricao> vazia e arquivo <arquivo>
Then retorne a mensagem de criacao da base <mensagem>

Examples:
|titulo|descricao|arquivo|mensagem|
|teste16| |/videos/video14.mp4|Descrição deve ser preenchida|
|teste17| |/videos/video03.mp4|Descrição deve ser preenchida|
|teste18| |/videos/video04.mp4|Descrição deve ser preenchida|
|teste19| |/videos/video05.mp4|Descrição deve ser preenchida|
|teste20| |/videos/video09.mp4|Descrição deve ser preenchida|
 
Scenario: Criação de base faltando os anexos
 
Given Um supervisor
When Ele quiser criar uma base de video com titulo <titulo>, descricao <descricao> e arquivo <arquivo> vazio
Then retorne a mensagem de criacao da base <mensagem>

Examples:
|titulo|descricao|arquivo|mensagem|
|teste21|descricao21| |Arquivos não devem ser vazios|
|teste22|descricao22| |Arquivos não devem ser vazios|
|teste23|descricao23| |Arquivos não devem ser vazios|
|teste24|descricao24| |Arquivos não devem ser vazios|
|teste25|descricao25| |Arquivos não devem ser vazios|