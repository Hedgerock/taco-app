DELETE FROM ingredient_ref;
DELETE FROM tacos;
DELETE FROM taco_order;
DELETE FROM ingredients;

INSERT INTO ingredients
    (id, name, type)
VALUES
    ('FLTO', 'Flour Tortilla', 'WRAP'),
    ('COTO', 'Corn Tortilla', 'WRAP'),
    ('GRBF', 'Ground Beef', 'PROTEIN'),
    ('CARN', 'Carnitas', 'PROTEIN'),
    ('TMTO', 'Diced Tomatoes', 'VEGETABLES'),
    ('LETC', 'Lettuce', 'VEGETABLES'),
    ('CHED', 'Cheddar', 'CHEESE'),
    ('JACK', 'Monterrey Jack', 'CHEESE'),
    ('SLSA', 'Salsa', 'SAUCE'),
    ('SRCR', 'Sour Cream', 'SAUCE');