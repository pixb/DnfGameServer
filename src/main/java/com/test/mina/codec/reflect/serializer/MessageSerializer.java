package com.test.mina.codec.reflect.serializer;

import com.test.game.utils.ByteBuffUtil;
import com.test.mina.annotation.IntegerField;
import com.test.mina.annotation.ListField;
import com.test.mina.annotation.LongField;
import com.test.mina.annotation.ShortField;
import com.test.mina.annotation.StringField;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.util.List;
import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSerializer extends Serializer {
   private Logger logger = LoggerFactory.getLogger(MessageSerializer.class);
   private List<FieldCodecMeta> fieldsMeta;

   public static MessageSerializer valueOf(List<FieldCodecMeta> fieldsMeta) {
      MessageSerializer serializer = new MessageSerializer();
      serializer.fieldsMeta = fieldsMeta;
      return serializer;
   }

   public Object decode(IoBuffer in, Class<?> type, Class<?> wrapper) {
      in = in.order(ByteOrder.LITTLE_ENDIAN);

      try {
         Object bean = type.newInstance();

         for(FieldCodecMeta fieldMeta : this.fieldsMeta) {
            Field field = fieldMeta.getField();
            Serializer fieldCodec = fieldMeta.getSerializer();
            Object value = null;
            if (fieldCodec instanceof StringSerializer) {
               StringField stringField = (StringField)field.getAnnotation(StringField.class);
               StringSerializer stringSerializer = (StringSerializer)fieldCodec;
               if (stringField != null) {
                  if (stringField.value() == 1) {
                     value = stringSerializer.decodeShort(in);
                  } else if (stringField.value() == 100) {
                     value = ByteBuffUtil.readFullString(in);
                  } else {
                     value = fieldCodec.decode(in, fieldMeta.getType(), fieldMeta.getWrapper());
                  }
               } else {
                  value = fieldCodec.decode(in, fieldMeta.getType(), fieldMeta.getWrapper());
               }
            } else if (fieldCodec instanceof CollectionSerializer) {
               ListField listField = (ListField)field.getAnnotation(ListField.class);
               CollectionSerializer collectionSerializer = (CollectionSerializer)fieldCodec;
               if (listField != null) {
                  if (listField.value() == 1) {
                     value = collectionSerializer.decodeByte(in, fieldMeta.getType(), fieldMeta.getWrapper());
                  } else if (listField.value() == 3) {
                     value = collectionSerializer.decodeInt(in, fieldMeta.getType(), fieldMeta.getWrapper());
                  } else {
                     value = fieldCodec.decode(in, fieldMeta.getType(), fieldMeta.getWrapper());
                  }
               } else {
                  value = fieldCodec.decode(in, fieldMeta.getType(), fieldMeta.getWrapper());
               }
            } else {
               value = fieldCodec.decode(in, fieldMeta.getType(), fieldMeta.getWrapper());
            }

            field.set(bean, value);
         }

         return bean;
      } catch (Exception var12) {
         return null;
      }
   }

   public void encode(IoBuffer out, Object message, Class<?> wrapper) {
      out = out.order(ByteOrder.LITTLE_ENDIAN);
      if (message != null) {
         Object value = null;

         try {
            byte valueType = -1;

            for(FieldCodecMeta fieldMeta : this.fieldsMeta) {
               Field field = fieldMeta.getField();
               Serializer fieldCodec = fieldMeta.getSerializer();
               value = field.get(message);
               if (valueType != 1 && valueType != 6) {
                  if (valueType != 2 && valueType != 7 && valueType != 85) {
                     if (valueType == 4) {
                        fieldCodec = Serializer.getSerializer(String.class);
                     } else if (valueType == 3) {
                        fieldCodec = Serializer.getSerializer(Integer.class);
                     }
                  } else {
                     fieldCodec = Serializer.getSerializer(Short.class);
                  }
               } else {
                  fieldCodec = Serializer.getSerializer(Byte.class);
               }

               if (fieldCodec instanceof StringSerializer) {
                  StringField stringField = (StringField)field.getAnnotation(StringField.class);
                  StringSerializer stringSerializer = (StringSerializer)fieldCodec;
                  if (stringField != null) {
                     if (stringField.value() == 1) {
                        stringSerializer.encodeShort(out, (String)value);
                     } else if (stringField.value() == 100) {
                        ByteBuffUtil.writeFullString(out, (String)value);
                     } else {
                        fieldCodec.encode(out, value, fieldMeta.getWrapper());
                     }
                  } else {
                     fieldCodec.encode(out, value, fieldMeta.getWrapper());
                  }
               } else if (fieldCodec instanceof CollectionSerializer) {
                  ListField listField = (ListField)field.getAnnotation(ListField.class);
                  CollectionSerializer collectionSerializer = (CollectionSerializer)fieldCodec;
                  if (listField != null) {
                     collectionSerializer.encode(out, value, fieldMeta.getWrapper(), listField.value());
                  } else {
                     fieldCodec.encode(out, value, fieldMeta.getWrapper());
                  }
               } else if (fieldCodec instanceof ArraySerializer) {
                  ListField listField = (ListField)field.getAnnotation(ListField.class);
                  ArraySerializer arraySerializer = (ArraySerializer)fieldCodec;
                  if (listField != null) {
                     arraySerializer.encode(out, value, fieldMeta.getWrapper(), listField.value());
                  } else {
                     fieldCodec.encode(out, value, fieldMeta.getWrapper());
                  }
               } else if (fieldCodec instanceof LongSerializer) {
                  LongField longField = (LongField)field.getAnnotation(LongField.class);
                  if (longField != null && longField.value() == 1) {
                     LongSerializer longSerializer = (LongSerializer)fieldCodec;
                     longSerializer.encodeUnsignedInt(out, value, fieldMeta.getWrapper());
                  } else {
                     fieldCodec.encode(out, value, fieldMeta.getWrapper());
                  }
               } else if (fieldCodec instanceof IntSerializer) {
                  IntegerField integerField = (IntegerField)field.getAnnotation(IntegerField.class);
                  if (integerField != null && integerField.value() == 1) {
                     IntSerializer intSerializer = (IntSerializer)fieldCodec;
                     intSerializer.encodeUnsignedShort(out, value, fieldMeta.getWrapper());
                  } else {
                     fieldCodec.encode(out, value, fieldMeta.getWrapper());
                  }
               } else if (fieldCodec instanceof ShortSerializer) {
                  ShortField shortField = (ShortField)field.getAnnotation(ShortField.class);
                  if (shortField != null && shortField.value() == 1) {
                     ShortSerializer shortSerializer = (ShortSerializer)fieldCodec;
                     shortSerializer.encodeUnsignedByte(out, value, fieldMeta.getWrapper());
                  } else {
                     fieldCodec.encode(out, value, fieldMeta.getWrapper());
                  }
               } else {
                  fieldCodec.encode(out, value, fieldMeta.getWrapper());
               }

               if (field.getName().equals("VT")) {
                  valueType = (Byte)value;
               }
            }
         } catch (Exception e) {
            this.logger.error("{}{}{}", new Object[]{e.getStackTrace(), message, message.getClass().getName()});
         }

      }
   }
}
