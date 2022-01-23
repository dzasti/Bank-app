package com.bank.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalSerializer extends StdDeserializer<BigDecimal> {
    public BigDecimalSerializer(Class<?> vc){
        super(vc);
    }

    public BigDecimalSerializer() {
        this(null);
    }

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String floatString = jsonParser.getText();
        if (floatString.contains(",")) {
            floatString = floatString.replace(",", ".");
        }
        return new BigDecimal(floatString);
    }
}
