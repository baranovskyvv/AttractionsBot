package org.baranovskiy.attractions.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    ServiceBot service = new ServiceBot();

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        switch (message.getText()) {
            case "/Привет":
            case "/привет":
                sendMsg(message, "Привет!");
                break;
            case "/Покажи все города которые тебе известны!":
                sendMsg(message, service.findAllCities());
                break;
            default:
                sendMsg(message, service.findCity(message.getText()));
        }
    }

    private void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);

        try {
            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRowfirst = new KeyboardRow();
        KeyboardRow keyboardRowSecond = new KeyboardRow();

        keyboardRowfirst.add(new KeyboardButton("/Привет"));
        keyboardRowSecond.add(new KeyboardButton("/Покажи все города которые тебе известны!"));

        keyboardRowList.add(keyboardRowfirst);
        keyboardRowList.add(keyboardRowSecond);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", username, token);
    }

}
