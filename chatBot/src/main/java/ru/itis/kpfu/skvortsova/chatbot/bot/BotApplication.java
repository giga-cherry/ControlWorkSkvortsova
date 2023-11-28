package ru.itis.kpfu.skvortsova.chatbot.bot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.itis.kpfu.skvortsova.chatbot.bot.service.CurrencyService;
import ru.itis.kpfu.skvortsova.chatbot.bot.service.ForecastService;
import ru.itis.kpfu.skvortsova.chatbot.bot.view.*;
import ru.itis.kpfu.skvortsova.chatbot.bot.client.ChatClient;
import ru.itis.kpfu.skvortsova.chatbot.bot.model.UserConfig;
import ru.itis.kpfu.skvortsova.chatbot.bot.view.ChatView;
import ru.itis.kpfu.skvortsova.chatbot.bot.view.UserConfigView;

public class BotApplication extends Application {

    private StartView startView;
    private CommandsView commandsView;
    private CurrencyView currencyView;
    private WeatherView weatherView;
    private BorderPane root;
    private ForecastService forecastService;
    private CurrencyService currencyService;
    private UserConfig userConfig;
    private UserConfigView configView;
    private ChatView chatView;
    private ChatClient chatClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bot");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        BaseBotView.setBotApplication(this);
        commandsView = new CommandsView();
        startView = new StartView();
        currencyView = new CurrencyView();
        weatherView = new WeatherView();

//      тут инициализация сервисов
        forecastService= new ForecastService();
        currencyService = new CurrencyService();

        root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        setView(startView);

        configView = new UserConfigView();
        chatView = new ChatView();
        chatClient = new ChatClient(this);
    }

    public void setView(BaseBotView view) {
        root.setCenter(view.getView());
    }

    public CommandsView getCommandsView() {
        return commandsView;
    }

    public ForecastService getForecastService(){
        return forecastService;
    }

    public WeatherView getWeatherView(){
        return weatherView;
    }

    public CurrencyView getCurrencyView(){
        return currencyView;
    }

    public void end(){
        System.exit(1);
    }

    public CurrencyService getCurrencyService(){
        return currencyService;
    }

    public UserConfigView getUserConfigView(){
        return configView;
    }



    public void appendMessage(String message) {
        chatView.appendMessage(message);
    }

    public void startChat() {
        chatClient.start();
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public UserConfigView getConfigView() {
        return configView;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
