# 📱 DroidChat

### AndroidKotlinJetpack ComposeWebSockets

### 📋 Descrição

DroidChat é uma aplicação de chat em tempo real para Android, desenvolvida com tecnologias modernas e utilizando WebSockets para comunicação instantânea entre os usuários. Este projeto aplica os principais conceitos e práticas de desenvolvimento Android para criar uma experiência de chat fluida e responsiva.

### ✨ Funcionalidades principais
* 💬 Chat em tempo real entre usuários
* 👤 Perfis de usuário personalizáveis
* 🔔 Notificações instantâneas de novas mensagens
* 🌐 Comunicação bidirecional via WebSockets
* 🎨 Interface moderna construída com Jetpack Compose

### 🛠️ Tecnologias e bibliotecas
* UI: Jetpack Compose - Para criar interfaces de usuário modernas e reativas
* Injeção de dependência: Dagger Hilt - Para simplificar a injeção de dependências e facilitar testes
* Requisições HTTP: Ktor Client - Para realizar requisições REST para a API do backend
* Comunicação em tempo real: WebSockets - Implementação da comunicação bidirecional em tempo real
* Arquitetura: Repository Pattern - Para organizar as fontes de dados e fazer o gerenciamento de dados de maneira mais eficiente
* Design System: Topaz - Biblioteca de componentes visuais para Jetpack Compose

## 🏗️ Arquitetura do projeto

```kotlin
com.example.droidchat/
├── data/
|   ├── di/
│   ├── local/
│   ├── remote/
│   └── repository/
├── domain/
│   ├── model/
│   └── interface/
├── ui/
|   ├── di/
│   ├── components/
│   ├── screens/
│   └── theme/
└── util/

```



## 📱 DroidChat: Integração com o Design System Topaz

O projeto está usando uma lib que ainda não está publicada no maven, e você precisa gerar ela localmente.

# 🎨 Topaz Design System

DroidChat utiliza o Topaz Design System, uma biblioteca de componentes reutilizáveis para Jetpack Compose que proporciona uma experiência visual consistente.

## Como configurar o Topaz

### 1️⃣ Clone o repositório do Topaz:

```kotlin
git clone git@github.com:vitor0321/topaz.git
```

### 2️⃣ Publique a Library no Maven Local

Dentro da pasta do projeto Topaz, publique a library no Maven Local:

```kotlin
./gradlew publishToMavenLocal
```

### 3️⃣ Configure o Maven Local no Seu Projeto

No arquivo `settings.gradle.kts` do seu projeto, adicione o Maven Local para permitir que o `Gradle` encontre a biblioteca:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal() // 🔥 Adicione esta linha
    }
}
```

### 4️⃣ Adicione a Dependência do Topaz

Agora, no `build.gradle.kts` do módulo `app`, adicione a dependência:

```kotlin
dependencies {
    implementation("com.walcker.compose.ui:topaz:0.0.1")
}
```

### 5️⃣ Sincronize o Projeto
Após adicionar a dependência, sincronize o projeto (Sync Project with Gradle Files).


## 🚀 Usando os Componentes do Topaz
Agora, você pode começar a utilizar os componentes disponíveis. Exemplo:

```kotlin
@Composable
fun ExampleScreen() {
    TopazDividerHorizontal(
        size: TopazDividerSize = TopazDividerSize.Medium
    )
}
```

# 🚀 Como rodar o projeto

## Pré-requisitos
* Android Studio Hedgehog 2023.1.1 ou mais recente
* JDK 17 ou mais recente
* Android SDK 33+
* Dispositivo Android ou Emulador com API 24 Android 7.0 ou superior

## Passos para executar
* Configure o Topaz Design System conforme as instruções acima
* Clone o repositório do DroidChat:
```kotlin
git clone https://github.com/username/DroidChat.git
```

* Abra o projeto no Android Studio
* Sincronize o projeto com os arquivos Gradle
* Execute o aplicativo em um dispositivo ou emulador


## 🗺️ Roadmap
* Suporte a mensagens multimídia imagens, áudio
* Chamadas de vídeo e áudio
* Salas de chat em grupo
* Modo offline com sincronização
* Criptografia ponta-a-ponta

## 🤝 Como contribuir
* Faça um Fork do projeto
* Crie uma branch para sua feature git checkout -b feature/NovaMelhoria
* Commit suas alterações git commit -m 'Adiciona nova melhoria'
* Push para a branch git push origin feature/NovaMelhoria
* Abra um Pull Request


## Diretrizes de contribuição
* Siga o padrão de código do projeto
* Escreva testes para novas funcionalidades
* Atualize a documentação conforme necessário

## 📄 Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para detalhes.
⭐️ Se você gostou deste projeto, por favor, dê uma estrela no GitHub ⭐️

