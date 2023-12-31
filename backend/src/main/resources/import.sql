insert into "currency" ("name","symbol") values ('Euro', '€');
insert into "currency" ("name","symbol") values ('Dollar', '$');
insert into "measurement" ("name","weight","height","width","depth") values ('metrisch', 'kg', 'cm', 'cm', 'cm');
insert into "measurement" ("name","weight","height","width","depth") values ('imperial', 'lb', 'in', 'in', 'in');
insert into "language" ("name","currency_id","measurement_id","iso_code") values ('Deutsch', 1, 1,'de');
insert into "language" ("name","currency_id","measurement_id","iso_code") values ('English', 2, 2,'en');
insert into "language" ("name","currency_id","measurement_id","iso_code") values ('Français', 1, 1,'fr');
insert into "color" ("name") values ('Rot');
insert into "color" ("name") values ('Blau');
insert into "color" ("name") values ('Grün');
insert into "category" ("name") values('Stuhl');

-- Insert additional data into product table
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Luxuriöser Wellness-Massagestuhl', 'W123456', 80.00, 120.00, 899.00, 12.00, 60.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Premium Küchenmaschine "Kochkünstler"', 'K987654', 40.00, 35.00, 349.00, 8.00, 25.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Smart Home Sicherheitspaket "Sicherheitsengel"', 'S789012', 15.00, 10.00, 499.00, 2.00, 10.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Bequemer Relax-Sessel', 'S456789', 90.00, 100.00, 499.00, 8.50, 70.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Moderne LED-Stehlampe', 'L987654', 30.00, 180.00, 129.00, 3.00, 20.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Ultra HD Smart TV', 'TV123ABC', 60.00, 40.00, 1499.00, 20.00, 100.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Gemütliche Sofagarnitur', 'SGH789XYZ', 200.00, 90.00, 1999.00, 30.00, 250.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Designer Esstisch aus Eiche', 'ETAB456QWE', 150.00, 75.00, 799.00, 18.00, 90.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Kompakter Kaffeevollautomat', 'KV345ABC', 40.00, 45.00, 499.00, 10.00, 30.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Kabelloser Bluetooth-Lautsprecher', 'BL678MNO', 8.00, 15.00, 89.00, 0.60, 10.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Robuster Reiserucksack', 'RR456PQR', 25.00, 50.00, 129.00, 1.20, 30.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Smart Home Thermostat', 'ST789XYZ', 5.00, 5.00, 149.00, 0.30, 8.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('HD Überwachungskamera-Set', 'UC789ABC', 10.00, 8.00, 299.00, 1.00, 5.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Ergonomischer Bürostuhl', 'BS456DEF', 60.00, 100.00, 199.00, 15.00, 50.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Drahtloses Ladegerät für Smartphones', 'WL123GHI', 5.00, 2.00, 39.00, 0.20, 10.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Faltbarer E-Scooter', 'ESCOOT123', 20.00, 100.00, 599.00, 10.00, 15.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Smartwatch mit Fitness-Tracker', 'SWFIT456', 8.00, 5.00, 129.00, 0.50, 3.00);
INSERT INTO "product" ("name", "serial", "depth", "height", "price", "weight", "width") VALUES ('Multifunktions-Küchenmesser-Set', 'KMSET789', 2.00, 10.00, 49.00, 0.30, 3.00);

-- Insert additional data into translation table
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (1, 1, 'Erleben Sie ultimative Entspannung mit unserem luxuriösen Wellness-Massagestuhl.', 'Tauchen Sie ein in puren Luxus mit unserem Wellness-Massagestuhl. Die fortschrittliche Technologie bietet maßgeschneiderte Massagen für eine totale Entspannung. Der Stuhl passt sich perfekt Ihrer Körperform an und bietet ein unvergleichliches Massageerlebnis. Machen Sie Wellness zu einem täglichen Ritual!');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (1, 2, 'Experience ultimate relaxation with our luxurious wellness massage chair.', 'Immerse yourself in pure luxury with our wellness massage chair. Advanced technology offers tailored massages for total relaxation. The chair adapts perfectly to your body shape and offers an unparalleled massage experience. Make wellness a daily ritual!');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (2, 1, 'Das Herzstück Ihrer Küche – unsere Premium Küchenmaschine "Kochkünstler".', 'Mit unserer Premium Küchenmaschine "Kochkünstler" wird Kochen zum Vergnügen. Die leistungsstarke Maschine ist vielseitig einsetzbar und erleichtert Ihnen die Zubereitung von Mahlzeiten. Ob Teig kneten, Gemüse schneiden oder Sahne schlagen – mit dieser Küchenmaschine meistern Sie jede kulinarische Herausforderung.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (3, 1, 'Ihre Sicherheit ist uns wichtig! Unser Smart Home Sicherheitspaket "Sicherheitsengel" bietet fortschrittlichen Schutz für Ihr Zuhause.', 'Mit unserem Smart Home Sicherheitspaket "Sicherheitsengel" schaffen Sie ein rundum sicheres Zuhause. Die intelligente Technologie ermöglicht eine einfache Überwachung, egal wo Sie sich gerade befinden. Schützen Sie Ihre Familie und Ihr Eigentum mit diesem hochmodernen Sicherheitssystem. Sicherheit hat noch nie so gut ausgesehen!');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (4, 1, 'Entspannen Sie in unserem bequemen Relax-Sessel.', 'Tauchen Sie ein in pures Komfortvergnügen mit unserem Relax-Sessel. Die ideale Wahl für gemütliche Stunden zu Hause.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (4, 2, 'Relax in our comfortable recliner chair.', 'Immerse yourself in pure comfort with our relax armchair. The ideal choice for cozy hours at home.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (5, 1, 'Schaffen Sie eine moderne Atmosphäre mit unserer LED-Stehlampe.', 'Mit unserer LED-Stehlampe setzen Sie stilvolle Akzente in Ihrem Zuhause. Das moderne Design und die energiesparende LED-Technologie vereinen sich zu einem perfekten Lichterlebnis.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (5, 3, 'Créez une atmosphère moderne avec notre lampadaire à LED.', 'Avec notre lampadaire LED, vous mettez des accents élégants dans votre maison. Le design moderne et la technologie LED à faible consommation d''énergie s''unissent pour créer une expérience d''éclairage parfaite.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (6, 1, 'Erleben Sie Entertainment in Ultra HD-Qualität mit unserem Smart TV.', 'Tauchen Sie ein in die Welt des hochauflösenden Fernsehens mit unserem Ultra HD Smart TV. Genießen Sie klare Bilder und smarte Funktionen für ein ultimatives Fernseherlebnis.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (7, 1, 'Genießen Sie entspannte Stunden auf unserer gemütlichen Sofagarnitur.', 'Unsere Sofagarnitur lädt zum Verweilen ein. Hochwertige Materialien und bequeme Polsterung sorgen für maximalen Komfort.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (8, 1, 'Essen Sie stilvoll mit unserem Designer Esstisch aus Eiche.', 'Mit unserem Esstisch aus Eiche setzen Sie elegante Akzente in Ihrem Essbereich. Das zeitlose Design passt zu jeder Einrichtung.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (9, 1, 'Bereiten Sie köstlichen Kaffee mit unserem kompakten Kaffeevollautomaten zu.', 'Der Kaffeevollautomat kombiniert Kompaktheit mit Leistung. Genießen Sie perfekten Kaffee auf Knopfdruck.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (10, 1, 'Genießen Sie kabellosen Sound mit unserem Bluetooth-Lautsprecher.', 'Unser Bluetooth-Lautsprecher bietet klaren Sound ohne lästige Kabel. Perfekt für Musikliebhaber unterwegs.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (11, 1, 'Reisen Sie mit Stil und Komfort mit unserem robusten Reiserucksack.', 'Unser Reiserucksack ist der ideale Begleiter für Abenteuer und Reisen. Robust, funktional und stylisch.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (12, 1, 'Optimieren Sie Ihr Raumklima mit unserem Smart Home Thermostat.', 'Das Smart Home Thermostat ermöglicht die bequeme Steuerung Ihrer Raumtemperatur. Sparen Sie Energie und erhöhen Sie Ihren Wohnkomfort.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (13, 1, 'Überwachen Sie Ihr Zuhause in HD-Qualität mit unserem Kamera-Set.', 'Das Überwachungskamera-Set bietet hochauflösende Aufnahmen für eine effektive Sicherheitsüberwachung.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (14, 1, 'Arbeiten Sie komfortabel mit unserem ergonomischen Bürostuhl.', 'Der Bürostuhl bietet optimalen Sitzkomfort und unterstützt eine gesunde Sitzhaltung während der Arbeit.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (15, 1, 'Laden Sie Ihr Smartphone kabellos mit unserem drahtlosen Ladegerät.', 'Das drahtlose Ladegerät ermöglicht bequemes und schnelles Aufladen ohne lästige Kabel. Ideal für den modernen Lebensstil.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (16, 1, 'Erkunden Sie die Stadt mit unserem faltbaren E-Scooter.', 'Der E-Scooter ist leicht, faltbar und ideal für die Fortbewegung in der Stadt. Entdecken Sie die Freiheit der Elektromobilität.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (17, 1, 'Bleiben Sie fit mit unserer Smartwatch und dem integrierten Fitness-Tracker.', 'Die Smartwatch bietet nicht nur stilvolles Design, sondern auch praktische Funktionen für Ihre Gesundheit und Fitness.');
INSERT INTO "translation" ("product_id", "language_id", "short_description", "long_description") VALUES (18, 1, 'Schneiden Sie mühelos mit unserem multifunktionalen Küchenmesser-Set.', 'Das Küchenmesser-Set vereint Funktionalität und Präzision. Perfekt für jede kulinarische Herausforderung in der Küche.');

INSERT INTO "product_categories" ("category_id", "product_id") VALUES(1, 1);
INSERT INTO "product_colors" ("color_id", "product_id") VALUES (1, 1);

INSERT INTO "product_categories" ("category_id", "product_id") VALUES(1, 2);
INSERT INTO "product_colors" ("color_id", "product_id") VALUES (2, 2);
INSERT INTO "product_colors" ("color_id", "product_id") VALUES (3, 2);