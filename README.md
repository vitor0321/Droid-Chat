# 📱 DroidChat
DroidChat é uma aplicação de chat em tempo real para Android, desenvolvida com tecnologias modernas e utilizando WebSockets para comunicação instantânea entre os usuários. 

Este projeto é um aplicativo de chat funcional, aplicando os principais conceitos e práticas de desenvolvimento Android.

## 🚀 Desenvolvimento:

* WebSockets: Como implementar a comunicação em tempo real entre os usuários do aplicativo de chat.
* Jetpack Compose: Criação de interfaces de usuário modernas, responsivas e declarativas.
* Injeção de Dependência com Dagger Hilt: Simplificando a gestão de dependências no projeto.
* Ktor: Utilização para fazer requisições de rede REST.
* Repository Pattern: Organização e abstração de dados para um código mais limpo e sustentável.
* Componentes de Arquitetura: Como estruturar a aplicação para melhorar sua manutenção e escalabilidade.

## 🖥️ Tecnologias principais:

* Jetpack Compose: Para criar interfaces de usuário modernas e reativas.
* Dagger Hilt: Para simplificar a injeção de dependências e facilitar testes.
* Ktor: Para realizar requisições REST para a API do backend.
* WebSockets: Implementação da comunicação bidirecional em tempo real, permitindo que as mensagens sejam trocadas instantaneamente entre os usuários.
* Repository Pattern: Para organizar as fontes de dados e fazer o gerenciamento de dados de maneira mais eficiente.

## 📱 DroidChat: Integração com o Design System Topaz

O projeto está usando uma lib que ainda não está publicada no maven, e você precisa gerar ela localmente.

# Topaz Design System Library 🎨🚀

Topaz é um Design System de componentes reutilizáveis para Jetpack Compose, projetada para facilitar o desenvolvimento de UIs modernas no Android e Kotlin Multiplatform.


## 📥 Instalação Local

### 1️⃣ Clone o Repositório

Primeiro, faça o clone do projeto:

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

## 📌 Nota: No futuro, disponibilizaremos mais componentes e documentações detalhadas.

### 📄 Contribuição
Sinta-se à vontade para abrir issues, pull requests e sugestões para melhorar a biblioteca!

## 📌 Repositório: [GitHub - Topaz](https://github.com/vitor0321/topaz)

# 🔥 Agora você está pronto para usar o Topaz UI! 🚀🎨
Se precisar de suporte, entre em contato ou abra uma issue no GitHub!
