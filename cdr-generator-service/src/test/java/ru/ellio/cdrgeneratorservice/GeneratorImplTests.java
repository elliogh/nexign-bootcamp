package ru.ellio.cdrgeneratorservice;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import ru.ellio.cdrgeneratorservice.service.GeneratorImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class GeneratorImplTests {

  private Environment env;
  @Value("${generator.options.amountPhones}")
  private int amountPhones;

  @SneakyThrows
  @Test
  @DisplayName("Проверка корректности телефона")
  void testCheckPhoneNumber() {
    // Создаем конструктор класса
    Constructor<GeneratorImpl> constructor = GeneratorImpl.class.getConstructor();
    GeneratorImpl generatorImplInstance = constructor.newInstance();

    Field amountPhonesField = GeneratorImpl.class.getDeclaredField("amountPhones");
    amountPhonesField.setAccessible(true);
    amountPhonesField.set(generatorImplInstance, 500);
    assertEquals(500, amountPhonesField.get(generatorImplInstance));

    // Меняем доступ для приватного метода и записываем возвращаемое значение
    Method phonesMethod = GeneratorImpl.class.getDeclaredMethod("generatePhoneNumbers");
    phonesMethod.setAccessible(true);
    List<String> phones = (List<String>) phonesMethod.invoke(generatorImplInstance);

    // Проход по списку телефонов и проверка их корректности
    for (int i = 0; i < amountPhones; i++) {
      char firstNumber = phones.get(i).charAt(0);
      int countOfNumbers = phones.get(i).length();
      assertEquals('7', firstNumber);
      assertEquals(11, countOfNumbers);
    }
  }
}


