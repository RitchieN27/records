// @ts-check
const eslint = require("@eslint/js");
const tseslint = require("typescript-eslint");
const angular = require("angular-eslint");

module.exports = tseslint.config(
    {
      files: ["**/*.ts"],
      extends: [
        eslint.configs.recommended,
        ...tseslint.configs.recommended,
        ...tseslint.configs.stylistic,
        ...angular.configs.tsRecommended,
      ],
      processor: angular.processInlineTemplates,
      rules: {
        "@angular-eslint/directive-selector": [
          "error",
          {
            type: "attribute",
            prefix: "app",
            style: "camelCase",
          },
        ],
        "@angular-eslint/component-selector": [
          "error",
          {
            type: "element",
            prefix: "app",
            style: "kebab-case",
          },
        ],
        "@angular-eslint/prefer-standalone": [
          "off"
        ],
        "@typescript-eslint/explicit-function-return-type": "warn",
        "@typescript-eslint/explicit-member-accessibility": [
          "warn",
          {
            "accessibility": "explicit",
            "overrides": {
              "constructors": "no-public"
            }
          }
        ],
        "@typescript-eslint/quotes": [
          "error",
          "single",
          {
            "avoidEscape": true
          }
        ],
        "arrow-parens": [
          "off",
          "always"
        ],
        "import/order": "off",
        "rxjs/no-unsafe-takeuntil": [
          "error",
          {
            "allow": [
              "count",
              "defaultIfEmpty",
              "endWith",
              "every",
              "finalize",
              "finally",
              "isEmpty",
              "last",
              "max",
              "min",
              "publish",
              "publishBehvior",
              "publishLast",
              "publishReplay",
              "reduce",
              "share",
              "shareReplay",
              "skipLast",
              "takeLast",
              "throwIfEmpty",
              "toArray"
            ]
          }
        ],
        "jsdoc/newline-after-description": "off",
        "no-underscore-dangle": [
          2,
          {
            "allowAfterThis": true
          }
        ],
        "@typescript-eslint/member-ordering": "warn",
        "@angular-eslint/no-empty-lifecycle-method": "warn",
        "@typescript-eslint/no-unsafe-member-access": "off",
        "@typescript-eslint/no-explicit-any": "warn",
        "@typescript-eslint/no-unsafe-argument": "warn",
        "@typescript-eslint/no-unsafe-call": "warn",
        "@typescript-eslint/no-unsafe-assignment": "warn",
        "@typescript-eslint/no-floating-promises": "off",
        "@typescript-eslint/unbound-method": "warn",
        "@typescript-eslint/require-await": "warn",
        "@typescript-eslint/no-unsafe-return": "warn",
        "@typescript-eslint/no-unused-vars": "off",
        "no-prototype-builtins": "off",
        "@typescript-eslint/restrict-plus-operands": "off",
        "no-empty": "off",
        "import/no-deprecated": "off",
        "@angular-eslint/no-output-native": "warn",
        "no-extra-boolean-cast": "off",
        "no-case-declarations": "off",
        "no-useless-escape": "off",
        "@typescript-eslint/no-misused-promises": "off",
        "@typescript-eslint/no-redundant-type-constituents": "off",
        "prefer-spread": "off",
        "no-unsafe-optional-chaining": "warn",
        "@typescript-eslint/restrict-template-expressions": "off",
        "@typescript-eslint/no-base-to-string": "off",
        "@typescript-eslint/no-unsafe-enum-comparison": "warn",
        "@typescript-eslint/naming-convention": [
          "error",
          {
            "selector": "variable",
            "leadingUnderscore": "allow",
            "format": [
              "camelCase",
              "PascalCase",
              "UPPER_CASE"
            ]
          },
          {
            "selector": "enumMember",
            "format": [
              "PascalCase",
              "UPPER_CASE",
              "camelCase"
            ]
          }
        ],
        "rxjs/no-exposed-subjects": [
          "warn",
          {
            "allowProtected": true
          }
        ]
      },
    },
    {
      files: ["**/*.html"],
      extends: [
        ...angular.configs.templateRecommended,
        ...angular.configs.templateAccessibility,
      ],
      rules: {},
    }
);
